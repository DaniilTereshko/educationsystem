package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ошибка валидации поля")
public record FieldErrorResponse(String field, String message) { }