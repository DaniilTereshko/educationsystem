package com.modsen.educationsystem.service.validation.impl;

import com.modsen.educationsystem.common.exception.InvalidRequestException;
import com.modsen.educationsystem.service.validation.QuestionValidator;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import org.springframework.stereotype.Component;

import static com.modsen.educationsystem.common.util.ExceptionMessage.QUESTION_TEXT_ANSWER_MIN_ELEMENTS_REQUIREMENTS;
import static com.modsen.educationsystem.common.util.ExceptionMessage.QUESTION_TEXT_ANSWER_REQUIREMENTS;

@Component("TEXT")
public class TextQuestionValidator implements QuestionValidator {
    @Override
    public void validate(final CreateQuestionRequest request) {
        if (request.getAnswers() == null || request.getAnswers().size() != 1) {
            throw new InvalidRequestException(QUESTION_TEXT_ANSWER_MIN_ELEMENTS_REQUIREMENTS);
        }
        if (!request.getAnswers().getFirst().getIsCorrect()) {
            throw new InvalidRequestException(QUESTION_TEXT_ANSWER_REQUIREMENTS);
        }
    }
}
