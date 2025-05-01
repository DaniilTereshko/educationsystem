package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.request.TestRequest;

import java.util.List;
import java.util.UUID;

public interface TestService {
    Test create(Long courseId, TestRequest request);

    Test update(Long id, TestRequest request);

    void delete(Long id);

    List<Test> getTestsByCourse(Long courseId);

    Test getOrThrow(final Long id);
}