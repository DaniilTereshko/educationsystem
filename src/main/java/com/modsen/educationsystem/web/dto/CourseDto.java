package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Курс")
public class CourseDto {

    @Schema(description = "Уникальный идентификатор курса")
    private Long id;
    @Schema(description = "Название курса")
    private String title;
    @Schema(description = "Подробное описание курса")
    private String description;
    @Schema(description = "Информация о создателе курса")
    private UserDto createdBy;
    @Schema(description = "Дата и время создания курса")
    private LocalDateTime createdAt;

}