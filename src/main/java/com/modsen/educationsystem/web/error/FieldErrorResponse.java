package com.modsen.educationsystem.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ошибка валидации поля")
public record FieldErrorResponse(
        @Schema(description = "Название поля", example = "email")
        String field,

        @Schema(description = "Сообщение об ошибке", example = "Email должен быть валидным")
        String message
) { }