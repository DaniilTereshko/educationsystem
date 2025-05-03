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
public class UserAnswerId implements Serializable {
    private Long resultId;
    private Long questionId;

    public static UserAnswerId of(final Long resultId, final Long questionId) {
        return new UserAnswerId(resultId, questionId);
    }
}