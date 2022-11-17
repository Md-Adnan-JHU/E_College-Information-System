package com.adnan.controller;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.StudentReturnDTO;
import com.adnan.dto.UpdateStudentDTO;
import com.adnan.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
        ("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/getStudent/{studentId}")
    @ApiOperation("Get a Student via StudentId")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public StudentReturnDTO getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }


    @PutMapping("/updateStudent")
    @ApiOperation("Update Details of Student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public MessageResponse updateStudent(UpdateStudentDTO dto){
        return studentService.updateStudent(dto);
    }



}
