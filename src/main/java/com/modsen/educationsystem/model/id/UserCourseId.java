package com.modsen.educationsystem.model.id;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class UserCourseId implements Serializable {
    private UUID userId;
    private Long courseId;
}