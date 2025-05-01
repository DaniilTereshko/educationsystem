package com.modsen.educationsystem.model;

import com.modsen.educationsystem.model.id.UserCourseId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserCourse {

    @EmbeddedId
    private UserCourseId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @Column(nullable = false)
    private LocalDateTime assignedAt;

    @Column(nullable = false)
    private Boolean isCompleted = false;
}
