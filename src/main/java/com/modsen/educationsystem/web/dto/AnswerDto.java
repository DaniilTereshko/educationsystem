package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Ответ на вопрос")
public class AnswerDto {

    @Schema(description = "Идентификатор ответа", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Текст ответа", example = "Объектно-ориентированное программирование")
    private String answerText;

    @Schema(description = "Является ли ответ правильным", example = "true")
    private Boolean isCorrect;

    @Schema(description = "Идентификатор вопроса, к которому относится ответ", example = "5")
    private Long questionId;
}