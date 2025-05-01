package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Ошибка валидации")
public record ValidationErrorResponse(String timestamp, String errorCode, List<FieldErrorResponse> fieldErrors) {

    public ValidationErrorResponse(String message, List<FieldErrorResponse> fieldErrors) {
        this(LocalDateTime.now().toString(), message, fieldErrors);
    }

    public static ValidationErrorResponse of(String errorCode, List<FieldErrorResponse> fieldErrors) {
        return new ValidationErrorResponse(errorCode, fieldErrors);
    }

}