package com.modsen.educationsystem.model;

import com.modsen.educationsystem.model.id.UserAnswerId;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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

    @ElementCollection
    @CollectionTable(
            name = "user_answer_selected",
            joinColumns = {
                    @JoinColumn(name = "result_id", referencedColumnName = "result_id"),
                    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
            }
    )
    @Column(name = "answer_id")
    private Set<Long> userAnswerIds;

    @Column(nullable = false)
    private Boolean isCorrect;

}