package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.Answer;
import com.modsen.educationsystem.web.dto.AnswerDto;
import com.modsen.educationsystem.web.request.AnswerRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = UserMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AnswerMapper {

    @Mapping(target = "questionId", source = "question.id")
    AnswerDto toDto(Answer entity);
    @Mapping(target = "questionId", source = "question.id")
    List<AnswerDto> toDto(List<Answer> entity);

    Answer toEntity(AnswerDto dto);
    List<Answer> toEntity(List<AnswerDto> dto);

    Answer fromAnswerRequest(AnswerRequest request);
}