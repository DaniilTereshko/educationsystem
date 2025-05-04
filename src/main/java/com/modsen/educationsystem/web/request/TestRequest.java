package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import static com.modsen.educationsystem.common.util.ValidationMessage.TEST_PASSING_SCORE_NEGATIVE;
import static com.modsen.educationsystem.common.util.ValidationMessage.TEST_TIME_LIMIT_NEGATIVE;
import static com.modsen.educationsystem.common.util.ValidationMessage.TEST_TITLE_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.TEST_TITLE_INCORRECT_LENGTH;

@Getter
@Setter
@Schema(description = "Запрос на создание/обновление теста")
public class TestRequest {

    @Size(min = 1, max = 100, message = TEST_TITLE_INCORRECT_LENGTH)
    @NotBlank(message = TEST_TITLE_EMPTY)
    @Schema(
            description = "Название теста",
            example = "Final Java Exam",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    @PositiveOrZero(message = TEST_TIME_LIMIT_NEGATIVE)
    @Schema(
            description = "Лимит времени на прохождение (в минутах)",
            example = "60",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Integer timeLimitMinutes;

    @PositiveOrZero(message = TEST_PASSING_SCORE_NEGATIVE)
    @Schema(
            description = "Проходной балл",
            example = "70",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Integer passingScore;

    @Min(value = 1, message = "Максимум попыток должен быть не менее 1")
    @Max(value = 100, message = "Максимум попыток не может превышать 100")
    @Schema(
            description = "Максимальное количество попыток на тест",
            example = "3",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Integer maxAttempts;

}
