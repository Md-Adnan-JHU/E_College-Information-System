package com.adnan.controller;

import com.adnan.dto.*;
import com.adnan.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
        ("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("create")
    @ApiOperation("Create New Student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @GetMapping("/getStudent/{studentId}")
    @ApiOperation("Get a Student via StudentId")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public StudentReturnDTO getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/getDetailedList/{courseId}")
    @ApiOperation("Get List of Students based on Course via CourseId")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentDetailedListDTO getStudentBasedOnCourse(@PathVariable Long courseId) {
        return studentService.getStudents(courseId);
    }

    @GetMapping("/getList/{courseId}")
    @ApiOperation("Get List of Students based on Course via CourseId")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentListDTO getStudentsList(@PathVariable Long courseId) {
        return studentService.getStudentsList(courseId);
    }

    @PutMapping("/updateStudent")
    @ApiOperation("Update Details of Student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public MessageResponse updateStudent(UpdateStudentDTO dto){
        return studentService.updateStudent(dto);
    }

    @PutMapping("/addCourse")
    @ApiOperation("Add course details in existing Student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse addCourse(@RequestBody AddCourseToStudentDTO dto) {
        return studentService.addCourse(dto);
    }

    @PutMapping("/addDepartment")
    @ApiOperation("Add Department details in existing Student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse addDepartment(@RequestBody AddDeptToStudentDTO dto) {
        return studentService.addDepartment(dto);
    }


}
