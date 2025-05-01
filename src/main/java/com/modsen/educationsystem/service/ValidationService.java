package com.modsen.educationsystem.service;

import com.modsen.educationsystem.web.request.CreateQuestionRequest;

public interface ValidationService {

    void validatePaginationParams(int page, int size);

    void validateQuestion(CreateQuestionRequest request);
}