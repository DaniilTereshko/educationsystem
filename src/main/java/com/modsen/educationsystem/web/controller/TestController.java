package com.modsen.educationsystem.web.controller;

import com.modsen.educationsystem.service.TestService;
import com.modsen.educationsystem.web.dto.TestDto;
import com.modsen.educationsystem.web.mapper.TestMapper;
import com.modsen.educationsystem.web.request.TestRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Operation(
            summary = "Получение тестов курса",
            description = "Получить список всех тестов для указанного курса"
    )
    @GetMapping("/course/{courseId}")
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long id) {
        testService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
