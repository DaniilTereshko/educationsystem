package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.modsen.educationsystem.common.util.ValidationMessage.ANSWER_CORRECTNESS_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.ANSWER_TEXT_EMPTY;

@Getter
@Setter
@Schema(description = "Запрос на создание/обновление ответа")
public class AnswerRequest {

    @NotBlank(message = ANSWER_TEXT_EMPTY)
    @Schema(description = "Текст ответа", example = "Объектно-ориентированное программирование", requiredMode = Schema.RequiredMode.REQUIRED)
    private String answerText;

    @NotNull(message = ANSWER_CORRECTNESS_EMPTY)
    @Schema(description = "Является ли ответ правильным", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCorrect;
}