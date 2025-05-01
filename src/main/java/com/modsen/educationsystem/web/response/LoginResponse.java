package com.modsen.educationsystem.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Ответ, содержащий токены доступа и обновления")
public class LoginResponse {

    @Schema(description = "Токен доступа для аутентификации", example = "eyJhbGciOiJIUzI1...")
    private String access;

    @Schema(description = "Токен обновления для получения новых токенов доступа", example = "dGhpcyBpcyBhIHJlZnJlc2g...")
    private String refresh;

}