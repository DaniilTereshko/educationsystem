package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.repository.TestRepository;
import com.modsen.educationsystem.service.CourseService;
import com.modsen.educationsystem.service.TestService;
import com.modsen.educationsystem.web.request.TestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final CourseService courseService;
    private final TestRepository testRepository;

    @Override
    public Test create(final Long courseId, final TestRequest request) {
        var course = courseService.getOrThrow(courseId);
        var test = new Test();

        test.setTitle(request.getTitle());
        test.setTimeLimitMinutes(request.getTimeLimitMinutes());
        test.setPassingScore(request.getPassingScore());
        test.setCourse(course);

        return testRepository.save(test);
    }

    @Override
    public Test update(final Long id, final TestRequest request) {
        var test = getOrThrow(id);

        test.setTitle(request.getTitle());
        test.setTimeLimitMinutes(request.getTimeLimitMinutes());
        test.setPassingScore(request.getPassingScore());

        return testRepository.save(test);
    }

    @Override
    public void delete(final Long id) {
        var course = getOrThrow(id);
        testRepository.delete(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByCourse(final Long courseId) {
        courseService.getOrThrow(courseId);
        return testRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Test getOrThrow(final Long id) {
        return testRepository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }
}
