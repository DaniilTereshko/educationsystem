package com.modsen.educationsystem.web.controller;

import com.modsen.educationsystem.service.AuthValidationService;
import com.modsen.educationsystem.service.QuestionService;
import com.modsen.educationsystem.web.dto.QuestionDto;
import com.modsen.educationsystem.web.mapper.QuestionMapper;
import com.modsen.educationsystem.web.request.CreateQuestionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Вопросы тестов", description = "Контроллер для операций над вопросами тестов")
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AuthValidationService authValidationService;

    @Operation(
            summary = "Получение вопросов теста",
            description = "Получить список всех вопросов для указанного теста"
    )
    @GetMapping("/tests/{testId}")
    @PreAuthorize("@authValidationService.isSubscribedToTest(#testId)")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<QuestionDto>> getTestQuestions(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long testId) {
        var questions = questionService.getQuestionsByTest(testId);
        var dtos = questionMapper.toDto(questions);
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Создание вопроса для теста",
            description = "Создать новый вопрос для указанного теста"
    )
    @PostMapping("/tests/{testId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER')")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<QuestionDto> create(
            @Parameter(description = "Идентификатор теста", required = true)
            @PathVariable final Long testId,
            @RequestBody final CreateQuestionRequest request
    ) {
        var questions = questionService.save(testId, request);
        var dto = questionMapper.toDto(questions);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Operation(
            summary = "Удаление вопроса",
            description = "Удалить существующий вопрос"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор вопроса", required = true)
            @PathVariable final Long id) {
        questionService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}