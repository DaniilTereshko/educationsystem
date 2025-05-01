package com.modsen.educationsystem.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.modsen.educationsystem.common.util.ValidationMessage.EMAIL_FIELD_IS_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.EMAIL_INCORRECT_LENGTH;
import static com.modsen.educationsystem.common.util.ValidationMessage.PASSWORD_FIELD_IS_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.PASSWORD_INCORRECT_LENGTH;
import static com.modsen.educationsystem.common.util.ValidationMessage.USERNAME_FIELD_IS_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.USERNAME_INCORRECT_LENGTH;

@Getter
@Setter
@Schema(description = "Запрос на регистрацию пользователя")
public class RegistrationRequest {

    @NotBlank(message = EMAIL_FIELD_IS_EMPTY)
    @Size(min = 1, max = 100, message = EMAIL_INCORRECT_LENGTH)
    @Schema(
            description = "Почта пользователя",
            example = "example@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank(message = USERNAME_FIELD_IS_EMPTY)
    @Size(min = 1, max = 50, message = USERNAME_INCORRECT_LENGTH)
    @Schema(
            description = "Имя пользователя",
            example = "user123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String username;

    @NotBlank(message = PASSWORD_FIELD_IS_EMPTY)
    @Size(min = 6, message = PASSWORD_INCORRECT_LENGTH)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(
            description = "Пароль пользователя",
            example = "securePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

}