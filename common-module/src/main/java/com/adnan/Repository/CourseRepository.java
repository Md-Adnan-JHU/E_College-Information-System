package com.adnan.Repository;

import com.adnan.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    String findByName(String courseName);
}
