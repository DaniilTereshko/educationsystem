package com.modsen.educationsystem.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.modsen.educationsystem.common.util.ValidationMessage.COURSE_DESCRIPTION_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.COURSE_DESCRIPTION_INCORRECT_LENGTH;
import static com.modsen.educationsystem.common.util.ValidationMessage.COURSE_TITLE_EMPTY;
import static com.modsen.educationsystem.common.util.ValidationMessage.COURSE_TITLE_INCORRECT_LENGTH;

@Getter
@Setter
@Schema(description = "Запрос на создание/обновление курса")
public class CourseRequest {

    @Size(min = 1, max = 100, message = COURSE_TITLE_INCORRECT_LENGTH)
    @NotBlank(message = COURSE_TITLE_EMPTY)
    @Schema(
            description = "Название курса",
            example = "Java",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    @Size(min = 1, message = COURSE_DESCRIPTION_INCORRECT_LENGTH)
    @NotBlank(message = COURSE_DESCRIPTION_EMPTY)
    @Schema(
            description = "Описание курса",
            example = "Java Developer course",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String description;

}