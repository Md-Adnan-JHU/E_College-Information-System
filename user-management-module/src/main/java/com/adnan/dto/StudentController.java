package com.adnan.dto;

import com.adnan.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public StudentReturnDTO getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }


    @PutMapping("/updateStudent")
    @ApiOperation("Update Details of Student")
    public MessageResponse updateStudent(UpdateStudentDTO dto){
        return studentService.updateStudent(dto);
    }



}
