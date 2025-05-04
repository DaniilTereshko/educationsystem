package com.modsen.educationsystem.web.controller;

import com.modsen.educationsystem.service.AuthService;
import com.modsen.educationsystem.web.error.ErrorResponse;
import com.modsen.educationsystem.web.mapper.UserMapper;
import com.modsen.educationsystem.web.request.LoginRequest;
import com.modsen.educationsystem.web.request.RegistrationRequest;
import com.modsen.educationsystem.web.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Аутентификация", description = "Контроллер для операций регистрации и аутентификации пользователя")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать нового пользователя в системе"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Пользователь зарегистрирован"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Validated final RegistrationRequest dto) {
        var user = userMapper.fromRegistrationRequest(dto);
        authService.register(user);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет пользователю войти в систему, получив токены доступа и обновления"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная авторизация"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated final LoginRequest dto) {
        return authService.login(dto);
    }

    @Operation(
            summary = "Обновление токенов",
            description = "Позволяет обновить токены доступа и обновления, используя refresh токен"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Токены обновлены"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/refresh-token")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<LoginResponse> refreshToken(
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        return ResponseEntity.ok(authService.refreshToken(req, resp));
    }

}