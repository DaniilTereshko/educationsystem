package com.modsen.educationsystem.service.validation.impl;

import com.modsen.educationsystem.common.exception.InvalidRequestException;
import com.modsen.educationsystem.service.validation.QuestionValidator;
import com.modsen.educationsystem.web.request.AnswerRequest;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import org.springframework.stereotype.Component;

import static com.modsen.educationsystem.common.util.ExceptionMessage.QUESTION_MULTIPLE_CHOICE_MIN_ELEMENTS_REQUIREMENTS;
import static com.modsen.educationsystem.common.util.ExceptionMessage.QUESTION_MULTIPLE_CHOICE_REQUIREMENTS;

@Component("MULTIPLE_CHOICE")
public class MultipleChoiceQuestionValidator implements QuestionValidator {
    @Override
    public void validate(final CreateQuestionRequest request) {
        if (request.getAnswers() == null || request.getAnswers().size() < 2) {
            throw new InvalidRequestException(QUESTION_MULTIPLE_CHOICE_MIN_ELEMENTS_REQUIREMENTS);
        }
        long correctAnswers = request.getAnswers().stream()
                .filter(AnswerRequest::getIsCorrect)
                .count();
        if (correctAnswers < 1) {
            throw new InvalidRequestException(QUESTION_MULTIPLE_CHOICE_REQUIREMENTS);
        }
    }
}
