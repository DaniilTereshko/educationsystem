package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.Question;
import com.modsen.educationsystem.web.dto.QuestionDto;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = AnswerMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface QuestionMapper {

    @Mapping(target = "testId", source = "test.id")
    QuestionDto toDto(Question entity);
    @Mapping(target = "testId", source = "test.id")
    List<QuestionDto> toDto(List<Question> entity);

    Question toEntity(QuestionDto dto);

    @Mapping(target = "answers", ignore = true)
    Question fromQuestionRequest(CreateQuestionRequest request);
}