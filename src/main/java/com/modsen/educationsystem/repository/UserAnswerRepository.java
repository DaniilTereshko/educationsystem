package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}