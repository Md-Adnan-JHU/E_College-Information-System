package com.adnan.service;

import com.adnan.dto.*;

public interface StudentService {

    MessageResponse createStudent(StudentDTO studentDTO);

    StudentReturnDTO getStudent(Long studentId);

    StudentDetailedListDTO getStudents(Long courseId);

    MessageResponse addCourse(AddCourseToStudentDTO dto);

    MessageResponse addDepartment(AddDeptToStudentDTO dto);

    MessageResponse updateStudent(UpdateStudentDTO dto);

    StudentListDTO getStudentsList(Long courseId);
}
