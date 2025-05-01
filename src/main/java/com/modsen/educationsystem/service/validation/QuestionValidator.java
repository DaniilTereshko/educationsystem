package com.modsen.educationsystem.service.validation;

import com.modsen.educationsystem.web.request.CreateQuestionRequest;

public interface QuestionValidator {

    void validate(CreateQuestionRequest request);

}