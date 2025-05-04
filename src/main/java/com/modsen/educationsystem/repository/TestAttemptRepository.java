package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {

    long countByUserAndTest(User user, Test test);

    Optional<TestAttempt> findByUserAndTestAndStatus(User user, Test test, TestAttempt.AttemptStatus status);

    Optional<TestAttempt> findByTestIdAndUserIdAndStatus(Long testId, UUID userId, TestAttempt.AttemptStatus status);
}