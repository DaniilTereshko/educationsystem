package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.common.exception.InvalidRequestException;
import com.modsen.educationsystem.service.ValidationService;
import com.modsen.educationsystem.service.validation.QuestionValidator;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_REQUEST_PAGE_EMPTY_EXCEPTION;
import static com.modsen.educationsystem.common.util.ExceptionMessage.INVALID_REQUEST_SIZE_VALUE_EXCEPTION;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    public final Map<String, QuestionValidator> questionValidators;

    @Override
    public void validatePaginationParams(int page, int size) {
        if (page < 0) {
            throw new InvalidRequestException(INVALID_REQUEST_PAGE_EMPTY_EXCEPTION);
        }
        if (size < 1 || size > 100) {
            throw new InvalidRequestException(INVALID_REQUEST_SIZE_VALUE_EXCEPTION);
        }
    }

    @Override
    public void validateQuestion(final CreateQuestionRequest request) {
        var validator = questionValidators.get(request.getQuestionType().name());

        if (validator != null) {
            validator.validate(request);
        }
    }
}
