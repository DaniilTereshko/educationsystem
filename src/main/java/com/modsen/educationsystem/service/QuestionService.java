package com.modsen.educationsystem.service;

import com.modsen.educationsystem.model.Question;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    Question save(Long testId, CreateQuestionRequest request);

    void delete(Long id);

    Question getOrThrow(final Long id);

    List<Question> getQuestionsByTest(Long testId);

    PageDto<QuestionDto> getQuestionsByTest(Long testId, int page, int size);

}