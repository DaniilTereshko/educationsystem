package com.modsen.educationsystem.model.id;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseId implements Serializable {
    private UUID userId;
    private Long courseId;

    public static UserCourseId of(final UUID userId, final Long courseId) {
        return new UserCourseId(userId, courseId);
    }

}