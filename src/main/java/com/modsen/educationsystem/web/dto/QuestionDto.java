package com.modsen.educationsystem.web.dto;

import com.modsen.educationsystem.model.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Вопрос теста")
public class QuestionDto {

    @Schema(description = "Идентификатор вопроса", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Текст вопроса", example = "Что такое ООП?")
    private String questionText;

    @Schema(description = "Тип вопроса", example = "SINGLE_CHOICE")
    private Question.QuestionType questionType;

    @Schema(description = "Количество баллов за вопрос", example = "5")
    private Integer points;

    @Schema(description = "Идентификатор теста, к которому относится вопрос", example = "10")
    private Long testId;

    @Schema(description = "Список ответов")
    private List<AnswerDto> answers;
}
