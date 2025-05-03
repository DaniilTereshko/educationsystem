package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.web.dto.TestResultDto;
import com.modsen.educationsystem.web.request.TestRequest;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TestService {
    Test create(Long courseId, TestRequest request);

    Test update(Long id, TestRequest request);

    void delete(Long id);

    List<Test> getTestsByCourse(Long courseId);

    Test getOrThrow(Long id);

    TestAttempt start(Long id);

    TestResult submit(Long id, TestSubmissionRequest request);

    Page<TestResultDto> getTestResults(Long id, PageRequest of);
}