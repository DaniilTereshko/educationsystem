package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Question;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;

import java.util.List;

public interface QuestionService {
    Question save(Long testId, CreateQuestionRequest request);

    void delete(Long id);

    Question getOrThrow(final Long id);

    List<Question> getQuestionsByTest(Long testId);

}