package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Результат прохождения теста")
public class TestResultDto {

    @Schema(description = "ID результата",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Набранные баллы",
            example = "85")
    private Integer score;

    @Schema(description = "Дата и время завершения теста",
            example = "2023-05-15T10:45:00")
    private LocalDateTime completedAt;

    @Schema(description = "Затраченное время в секундах",
            example = "900")
    private Integer timeSpentSeconds;
}