package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Тест")
public class TestDto {

    @Schema(description = "Идентификатор теста")
    private Long id;

    @Schema(description = "Название теста", required = true)
    private String title;

    @Schema(description = "Лимит времени на прохождение (в минутах)")
    private Integer timeLimitMinutes;

    @Schema(description = "Проходной балл")
    private Integer passingScore;

    @Schema(description = "Максимальное количество попыток на тест")
    private Integer maxAttempts;
}