package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Унифицированный ответ ошибки")
public record ErrorResponse(
        @Schema(description = "Временная метка ошибки", example = "2024-05-20T14:30:45.123Z")
        String timestamp,

        @Schema(description = "Код ошибки", example = "USER_NOT_FOUND")
        String errorCode,

        @Schema(description = "Сообщение об ошибке", example = "Пользователь не найден")
        String message,

        @Schema(description = "Ошибки валидации (только для ошибок валидации)")
        List<FieldErrorResponse> fieldErrors
) {
    public ErrorResponse(String errorCode, String message) {
        this(LocalDateTime.now().toString(), errorCode, message, null);
    }

    public ErrorResponse(String errorCode, String message, List<FieldErrorResponse> fieldErrors) {
        this(LocalDateTime.now().toString(), errorCode, message, fieldErrors);
    }

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

    public static ErrorResponse of(String errorCode, String message, List<FieldErrorResponse> fieldErrors) {
        return new ErrorResponse(errorCode, message, fieldErrors);
    }
}