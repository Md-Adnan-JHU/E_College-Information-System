package com.adnan.Repository;


import com.adnan.entity.Course;
import com.adnan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByCourse(Course course);

    Student findByName(String studentName);
}
