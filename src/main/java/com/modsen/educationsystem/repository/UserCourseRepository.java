package com.modsen.educationsystem.repository;

import com.modsen.educationsystem.model.Course;
import com.modsen.educationsystem.model.UserCourse;
import com.modsen.educationsystem.model.id.UserCourseId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseId> {

    boolean existsById(UserCourseId id);
    @Query("SELECT uc.course FROM UserCourse uc WHERE uc.user.id = :userId")
    Page<Course> findCoursesByUserId(@Param("userId") UUID userId, PageRequest pageable);
}