package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.modsen.educationsystem.common.util.ValidationMessage.ANSWERS_EMPTY;

@Getter
@Setter
@Schema(description = "Запрос для отправки результатов теста")
public class TestSubmissionRequest {

    @NotEmpty(message = ANSWERS_EMPTY)
    @Schema(description = "Список ответов на вопросы теста", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<UserAnswerRequest> answers;

}