package com.modsen.educationsystem.model.id;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserAnswerId implements Serializable {
    private Long resultId;
    private Long questionId;
}