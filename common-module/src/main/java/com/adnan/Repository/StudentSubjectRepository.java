package com.adnan.Repository;

import com.adnan.entity.Student;
import com.adnan.entity.Student_Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<Student_Subject, Long> {

//        List<Student_Subject> findByStudentId(Student studentId);

        List<Student_Subject> findByStudent(Student studentInfo);
}
