package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.TestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    Page<TestResult> findByTestIdAndUserId(Long testId, UUID userId, PageRequest of);
}