package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Course;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.dto.CourseDto;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.request.CourseRequest;

import java.util.UUID;

public interface CourseService {
    Course create(CourseRequest request);

    Course get(Long id);
    PageDto<CourseDto> get(int page, int size);

    void delete(Long id);

    Course update(Long id, CourseRequest request);

    void assign(Long courseId, UUID userId);

    Course getOrThrow(final Long id);
}