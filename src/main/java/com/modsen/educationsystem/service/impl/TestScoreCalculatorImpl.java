package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Answer;
import com.modsen.educationsystem.model.Question;
import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.model.UserAnswer;
import com.modsen.educationsystem.model.id.UserAnswerId;
import com.modsen.educationsystem.repository.UserAnswerRepository;
import com.modsen.educationsystem.repository.QuestionRepository;
import com.modsen.educationsystem.service.TestScoreCalculator;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;
import com.modsen.educationsystem.web.request.UserAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class TestScoreCalculatorImpl implements TestScoreCalculator {

    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;

    @Override
    public int calculateTestScoreAnsSaveAnswers(final TestSubmissionRequest request, final TestResult testResult) {
        int totalScore = 0;
        var userAnswers = new ArrayList<UserAnswer>();

        for (UserAnswerRequest userAnswerRequest : request.getAnswers()) {
            var question = questionRepository.findById(userAnswerRequest.getQuestionId())
                    .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, userAnswerRequest.getQuestionId()));

            boolean isCorrect = checkAnswerCorrectness(question, userAnswerRequest);
            if (isCorrect) {
                totalScore += question.getPoints();
            }

            var userAnswer = new UserAnswer();
            userAnswer.setId(UserAnswerId.of(testResult.getId(), userAnswerRequest.getQuestionId()));
            userAnswer.setResult(testResult);
            userAnswer.setQuestion(question);
            userAnswer.setUserAnswer(userAnswerRequest.getAnswer());
            userAnswer.setUserAnswerIds(userAnswerRequest.getUserAnswerIds());
            userAnswer.setIsCorrect(isCorrect);

            userAnswers.add(userAnswer);
        }

        userAnswerRepository.saveAll(userAnswers);

        return totalScore;
    }

    private boolean checkAnswerCorrectness(Question question, UserAnswerRequest userAnswerRequest) {
        return switch (question.getQuestionType()) {
            case SINGLE_CHOICE -> checkSingleChoiceAnswer(question, userAnswerRequest.getUserAnswerIds());
            case MULTIPLE_CHOICE -> checkMultipleChoiceAnswer(question, userAnswerRequest.getUserAnswerIds());
            case TEXT -> checkTextAnswer(question, userAnswerRequest.getAnswer());
        };
    }

    private boolean checkSingleChoiceAnswer(Question question, Set<Long> userAnswerIds) {
        if (userAnswerIds == null || userAnswerIds.size() != 1) {
            return false;
        }

        var userAnswerId = userAnswerIds.iterator().next();
        return question.getAnswers().stream()
                .filter(Answer::getIsCorrect)
                .anyMatch(a -> a.getId().equals(userAnswerId));
    }

    private boolean checkMultipleChoiceAnswer(Question question, Set<Long> userAnswerIds) {
        if (userAnswerIds == null || userAnswerIds.isEmpty()) {
            return false;
        }

        var correctAnswers = question.getAnswers().stream()
                .filter(Answer::getIsCorrect)
                .map(Answer::getId)
                .collect(Collectors.toSet());

        return correctAnswers.equals(userAnswerIds);
    }

    private boolean checkTextAnswer(Question question, String userAnswerText) {
        if (userAnswerText == null || userAnswerText.isBlank()) {
            return false;
        }

        return question.getAnswers().stream()
                .filter(Answer::getIsCorrect)
                .anyMatch(a -> a.getAnswerText().equalsIgnoreCase(userAnswerText.trim()));
    }

}
