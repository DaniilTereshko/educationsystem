package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, Long> {
}