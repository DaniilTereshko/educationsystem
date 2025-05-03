package com.modsen.educationsystem.web.controller;

import com.modsen.educationsystem.service.AuthValidationService;
import com.modsen.educationsystem.service.TestService;
import com.modsen.educationsystem.web.dto.PageDto;
import com.modsen.educationsystem.web.dto.TestDto;
import com.modsen.educationsystem.web.dto.TestResultDto;
import com.modsen.educationsystem.web.mapper.TestMapper;
import com.modsen.educationsystem.web.mapper.TestResultMapper;
import com.modsen.educationsystem.web.request.TestRequest;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

import java.util.List;

@Tag(name = "Тесты", description = "Контроллер для операций над тестами")
@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final TestMapper testMapper;
    private final TestResultMapper testResultMapper;
    private final AuthValidationService authValidationService;

    @Operation(
            summary = "Получение тестов курса",
            description = "Получить список всех тестов для указанного курса"
    )
    @GetMapping("/course/{courseId}")
    @PreAuthorize("@authValidationService.isSubscribedToCourse(#courseId)")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<TestDto>> getCourseTests(
            @Parameter(description = "Идентификатор курса", required = true)
            @PathVariable final Long courseId) {
        var test = testService.getTestsByCourse(courseId);
        var dtos = testMapper.toDto(test);
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Создание теста для курса",
            description = "Создать новый тест для указанного курса"
    )
    @PostMapping("/courses/{courseId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TestDto> create(
            @Parameter(description = "Идентификатор курса", required = true)
            @PathVariable final Long courseId,
            @RequestBody final TestRequest request
    ) {
        var test = testService.create(courseId, request);
        var dto = testMapper.toDto(test);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Operation(
            summary = "Обновление теста",
            description = "Обновить существующий тест"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<TestDto> update(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id,
            @RequestBody final TestRequest request) {
        var test = testService.update(id, request);
        var dto = testMapper.toDto(test);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Удаление теста",
            description = "Удалить существующий тест"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id) {
        testService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    @Operation(
            summary = "Начать попытку прохождения теста",
            description = "Пользователь начинает попытку прохождения"
    )
    @PostMapping("/{id}/start")
    @PreAuthorize("@authValidationService.isSubscribedToTest(#id)")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> startTest(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id) {
        testService.start(id);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(
            summary = "Отправить результаты теста",
            description = "Пользователь отправляет свои ответы и завершает текущую активную попытку"
    )
    @PostMapping("/{id}/submit")
    @PreAuthorize("@authValidationService.isSubscribedToTest(#id)")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> submit(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id,
            @RequestBody TestSubmissionRequest request
    ) {
        var result = testService.submit(id, request);
        var dto = testResultMapper.toDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Operation(
            summary = "Получить результаты теста пользователя",
            description = "Возвращает список всех результатов прохождения указанного теста по пользователю"
    )
    @PreAuthorize("@authValidationService.isSubscribedToTest(#id)")
    @GetMapping("/{id}/results")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<PageDto<TestResultDto>> getTestResults(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id,
            @Parameter(description = "Номер страницы (пагинация)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Размер страницы (пагинация)")
            @RequestParam(defaultValue = "10") int size
    ) {
        var results = testService.getTestResults(id, PageRequest.of(page, size));
        return ResponseEntity.ok()
                .body(new PageDto<>(results));
    }
}
