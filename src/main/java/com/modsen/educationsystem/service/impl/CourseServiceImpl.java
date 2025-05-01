package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Course;
import com.modsen.educationsystem.model.UserCourse;
import com.modsen.educationsystem.model.id.UserCourseId;
import com.modsen.educationsystem.repository.CourseRepository;
import com.modsen.educationsystem.repository.UserCourseRepository;
import com.modsen.educationsystem.service.CourseService;
import com.modsen.educationsystem.service.UserService;
import com.modsen.educationsystem.service.ValidationService;
import com.modsen.educationsystem.web.dto.CourseDto;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.mapper.CourseMapper;
import com.modsen.educationsystem.web.request.CourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final ValidationService validationService;
    private final UserService userService;
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;

    @Override
    public Course create(final CourseRequest request) {
        var course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Course get(final Long id) {
        return getOrThrow(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<CourseDto> get(final int page, final int size) {
        validationService.validatePaginationParams(page, size);
        var pageRequest = PageRequest.of(page, size);

        Page<CourseDto> pageResult = courseRepository.findAll(pageRequest)
                .map(courseMapper::toDto);

        return new PageDto<>(pageResult);
    }

    @Override
    public void delete(final Long id) {
        var course = getOrThrow(id);
        courseRepository.delete(course);
    }

    @Override
    public Course update(final Long id, final CourseRequest request) {
        var course = getOrThrow(id);

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        return courseRepository.save(course);
    }

    @Override
    public void assign(final Long courseId, final UUID userId) {
        var course = getOrThrow(courseId);
        var user = userService.getOrThrow(userId);

        var userCourse = new UserCourse();

        userCourse.setId(UserCourseId.of(userId, courseId));
        userCourse.setCourse(course);
        userCourse.setUser(user);
        userCourse.setAssignedAt(LocalDateTime.now());

        userCourseRepository.save(userCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getOrThrow(final Long id) {
        return courseRepository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }
}