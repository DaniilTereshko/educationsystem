package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Ошибка валидации")
public record ValidationErrorResponse(
        @Schema(description = "Временная метка ошибки", example = "2024-05-20T14:30:45.123Z")
        String timestamp,

        @Schema(description = "Код ошибки", example = "VALIDATION_ERROR")
        String errorCode,

        @Schema(description = "Список ошибок полей")
        List<FieldErrorResponse> fieldErrors
) {
    public ValidationErrorResponse(String message, List<FieldErrorResponse> fieldErrors) {
        this(LocalDateTime.now().toString(), message, fieldErrors);
    }

    public static ValidationErrorResponse of(String errorCode, List<FieldErrorResponse> fieldErrors) {
        return new ValidationErrorResponse(errorCode, fieldErrors);
    }
}