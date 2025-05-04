package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Попытка прохождения теста")
public class TestAttemptDto {
    @Schema(description = "ID попытки", example = "1")
    private Long id;

    @Schema(description = "ID пользователя", example = "123e4567-e89b-12d3-a456-426614174000")
    private String userId;

    @Schema(description = "ID теста", example = "10")
    private Long testId;

    @Schema(description = "Время начала", example = "2024-06-01T12:00:00")
    private LocalDateTime startedAt;

    @Schema(description = "Время завершения", example = "2024-06-01T12:30:00")
    private LocalDateTime finishedAt;

    @Schema(description = "Статус попытки", example = "IN_PROGRESS")
    private String status;
} 