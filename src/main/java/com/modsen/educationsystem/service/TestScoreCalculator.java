package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;

public interface TestScoreCalculator {

    int calculateTestScoreAnsSaveAnswers(TestSubmissionRequest request, TestResult testResult);
}
