package com.modsen.educationsystem.web.controller;

import com.modsen.educationsystem.security.service.UserHolder;
import com.modsen.educationsystem.service.AuthValidationService;
import com.modsen.educationsystem.service.CourseService;
import com.modsen.educationsystem.web.dto.CourseDto;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.mapper.CourseMapper;
import com.modsen.educationsystem.web.request.CourseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.modsen.educationsystem.web.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Курсы", description = "Контроллер для операций над курсами")
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final AuthValidationService authValidationService;

    @Operation(
            summary = "Получение списка всех курсов",
            description = "Получить список всех курсов с пагинацией"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список курсов получен"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<PageDto<CourseDto>> get(
            @Parameter(description = "Номер страницы (начиная с 0)", example = "0")
            @RequestParam(defaultValue = "0") final int page,
            @Parameter(description = "Количество элементов на странице", example = "10")
            @RequestParam(defaultValue = "10") final int size
    ) {
        if (authValidationService.isManager()) {
            return ResponseEntity.ok(courseService.get(page, size));
        } else {
            return ResponseEntity.ok(courseService.getUserCourses(page, size));
        }
    }

    @Operation(
            summary = "Получение курса по идентификатору",
            description = "Получить курс по идентификатору"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Курс получен"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("@authValidationService.isSubscribedToCourse(#id)")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<CourseDto> get(
            @Parameter(description = "Идентификатор", required = true)
            @PathVariable final Long id
    ) {
        var course = courseService.get(id);
        var dto = courseMapper.toDto(course);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Создать курс",
            description = "Создать курс"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Курс создан"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<CourseDto> create(@RequestBody final CourseRequest request) {
        var course = courseService.create(request);
        var dto = courseMapper.toDto(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Operation(
            summary = "Обновить курс",
            description = "Обновить курс"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Курс обновлён"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<CourseDto> update(
            @Parameter(description = "Идентификатор", required = true)
            @PathVariable final Long id,
            @RequestBody final CourseRequest request
    ) {
        var course = courseService.update(id, request);
        var dto = courseMapper.toDto(course);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Удалить курс",
            description = "Удалить курс"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Курс удалён"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор", required = true)
            @PathVariable final Long id
    ) {
        courseService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    @Operation(
            summary = "Назначение курса пользователю",
            description = "Назначить курс пользователю"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Курс назначен"),
        @ApiResponse(responseCode = "400", description = "Ошибка пользователя/валидации", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Нет доступа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/{courseId}/assign/{userId}")
    @SecurityRequirement(name = "JWT")
    public void assign(
            @Parameter(description = "Идентификатор курса", required = true)
            @PathVariable final Long courseId,
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable final UUID userId
    ) {
        courseService.assign(courseId, userId);
    }

}
