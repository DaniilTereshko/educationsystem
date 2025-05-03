package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ошибка")
public record ErrorResponse(
        @Schema(description = "Временная метка ошибки", example = "2024-05-20T14:30:45.123Z")
        String timestamp,

        @Schema(description = "Код ошибки", example = "USER_NOT_FOUND")
        String errorCode,

        @Schema(description = "Сообщение об ошибке", example = "Пользователь не найден")
        String message
) {
    public ErrorResponse(String errorCode, String message) {
        this(LocalDateTime.now().toString(), errorCode, message);
    }

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}