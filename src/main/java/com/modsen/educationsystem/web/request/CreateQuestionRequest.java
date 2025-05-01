package com.modsen.educationsystem.web.request;

import com.modsen.educationsystem.model.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_ANSWERS_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_ANSWERS_LIMIT;
import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_POINTS_INVALID;
import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_TEXT_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.QUESTION_TYPE_EMPTY;

@Getter
@Setter
@Schema(description = "Запрос на создание/обновление вопроса")
public class CreateQuestionRequest {

    @NotBlank(message = QUESTION_TEXT_EMPTY)
    @Schema(description = "Текст вопроса", example = "Что такое ООП?", requiredMode = Schema.RequiredMode.REQUIRED)
    private String questionText;

    @NotNull(message = QUESTION_TYPE_EMPTY)
    @Schema(description = "Тип вопроса", example = "SINGLE_CHOICE", requiredMode = Schema.RequiredMode.REQUIRED)
    private Question.QuestionType questionType;

    @Min(value = 1, message = QUESTION_POINTS_INVALID)
    @Max(value = 5, message = QUESTION_POINTS_INVALID)
    @Schema(description = "Количество баллов за вопрос", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer points;

    @NotEmpty(message = QUESTION_ANSWERS_EMPTY)
    @Size(max = 8, message = QUESTION_ANSWERS_LIMIT)
    @Schema(description = "Список ответов", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<@Valid AnswerRequest> answers;
}
