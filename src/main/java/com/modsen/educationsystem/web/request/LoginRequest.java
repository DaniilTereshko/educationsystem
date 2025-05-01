package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import static com.modsen.educationsystem.common.util.ValidationMessage.LOGIN_PASSWORD_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.LOGIN_USERNAME_EMPTY;

@Getter
@Setter
@Schema(description = "Запрос на вход пользователя")
public class LoginRequest {

    @NotBlank(message = LOGIN_USERNAME_EMPTY)
    @Schema(
            description = "Имя пользователя",
            example = "user123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String username;

    @NotBlank(message = LOGIN_PASSWORD_EMPTY)
    @Schema(
            description = "Пароль пользователя",
            example = "securePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;
}