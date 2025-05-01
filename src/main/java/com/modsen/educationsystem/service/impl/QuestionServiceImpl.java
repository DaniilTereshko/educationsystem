package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.model.Answer;
import com.modsen.educationsystem.model.Question;
import com.modsen.educationsystem.repository.AnswerRepository;
import com.modsen.educationsystem.repository.QuestionRepository;
import com.modsen.educationsystem.service.QuestionService;
import com.modsen.educationsystem.service.TestService;
import com.modsen.educationsystem.service.ValidationService;
import com.modsen.educationsystem.web.mapper.AnswerMapper;
import com.modsen.educationsystem.web.mapper.QuestionMapper;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final TestService testService;
    private final ValidationService validationService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Override
    public Question save(final Long testId, final CreateQuestionRequest request) {
        var test = testService.getOrThrow(testId);

        validationService.validateQuestion(request);

        var question = questionMapper.fromQuestionRequest(request);
        question.setTest(test);

        var savedQuestion = questionRepository.save(question);

        if (request.getAnswers() != null && !request.getAnswers().isEmpty()) {
            var answers = createAnswers(request, savedQuestion);
            answerRepository.saveAll(answers);
            savedQuestion.setAnswers(answers);
        }

        return savedQuestion;
    }

    @Override
    public void delete(final Long id) {
        var question = getOrThrow(id);
        questionRepository.delete(question);
    }

    @Override
    @Transactional(readOnly = true)
    public Question getOrThrow(final Long id) {
        return questionRepository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }

    @Override
    public List<Question> getQuestionsByTest(final Long testId) {
        testService.getOrThrow(testId);
        return questionRepository.findByTestId(testId);
    }

    private List<Answer> createAnswers(final CreateQuestionRequest request, final Question question) {
        return request.getAnswers().stream()
                .map(answerReq -> {
                    var answer = answerMapper.fromAnswerRequest(answerReq);
                    answer.setQuestion(question);
                    return answer;
                })
                .toList();
    }

}