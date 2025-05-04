package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByIdAndDeletedFalse(Long id);
    Page<Course> findAllByDeletedFalse(PageRequest of);
}