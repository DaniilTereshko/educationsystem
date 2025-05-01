package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.UserCourse;
import com.modsen.educationsystem.model.id.UserCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseId> {
}