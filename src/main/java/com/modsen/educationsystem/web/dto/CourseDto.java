package com.modsen.educationsystem.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Курс")
public class CourseDto {

    private Long id;
    private String title;
    private String description;
    private UserDto createdBy;
    private LocalDateTime createdAt;

}