package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTestId(Long testId);

    Page<Question> findAllByTestId(Long testId, PageRequest pageRequest);
}