package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;

public interface ValidationService {

    void validatePaginationParams(int page, int size);

    void validateQuestion(CreateQuestionRequest request);

    void validateTestAttempts(Test test, User user);

    void validateNoActiveAttempts(Test test, User user);
}