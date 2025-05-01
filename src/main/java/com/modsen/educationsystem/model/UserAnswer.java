package com.modsen.educationsystem.model;

import com.modsen.educationsystem.model.id.UserAnswerId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class UserAnswer {

    @EmbeddedId
    private UserAnswerId id;

    @ManyToOne
    @MapsId("resultId")
    private TestResult result;

    @ManyToOne
    @MapsId("questionId")
    private Question question;

    private String userAnswer;

    @Column(nullable = false)
    private Boolean isCorrect;

}