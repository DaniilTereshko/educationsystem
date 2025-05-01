package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByCourseId(Long courseId);
}