package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.web.dto.TestResultDto;
import com.modsen.educationsystem.web.request.TestRequest;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.dto.TestDto;
import com.modsen.educationsystem.web.dto.TestAttemptDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TestService {
    Test create(Long courseId, TestRequest request);

    Test update(Long id, TestRequest request);

    void delete(Long id);

    Test getOrThrow(Long id);

    TestAttempt start(Long id);

    TestResult submit(Long id, TestSubmissionRequest request);

    Page<TestResultDto> getTestResults(Long id, PageRequest of);

    PageDto<TestDto> getTestsByCourse(Long courseId, int page, int size);

    TestAttemptDto getInProgressAttempt(Long testId);
}