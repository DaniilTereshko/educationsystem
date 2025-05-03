package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_ID_EMPTY;

@Getter
@Setter
@Schema(description = "Запрос ответа пользователя на вопрос")
public class UserAnswerRequest {

    @NotNull(message = QUESTION_ID_EMPTY)
    @Schema(description = "ID вопроса", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long questionId;

    @Schema(description = "Текст ответа", example = "Пример ответа")
    private String answer;

    @Schema(description = "Идентификатор выбранного пользователем ответа", example = "1")
    private Set<Long> userAnswerIds;
}