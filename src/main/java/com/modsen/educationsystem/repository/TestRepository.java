package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByCourseId(Long courseId);

    Page<Test> findAllByCourseId(Long courseId, PageRequest pageRequest);

    Optional<Test> findByIdAndDeletedFalse(Long id);

    List<Test> findByCourseIdAndDeletedFalse(Long courseId);

    Page<Test> findAllByCourseIdAndDeletedFalse(Long courseId, PageRequest pageRequest);
}