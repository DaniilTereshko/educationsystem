package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Answer;
import com.modsen.educationsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}