package com.adnan.serviceImpl;

import com.adnan.Repository.CourseRepository;
import com.adnan.Repository.DepartmentRepository;
import com.adnan.Repository.StudentFeeRepository;
import com.adnan.Repository.StudentRepository;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.StudentReturnDTO;
import com.adnan.dto.UpdateStudentDTO;
import com.adnan.entity.Student;
import com.adnan.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentFeeRepository studentFeeRepository;


    @Override
    @ApiOperation("Get Details of single Student")
    public StudentReturnDTO getStudent(Long studentId) {

       Student student =  studentRepository.findById(studentId).orElse(null);

       if(student == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

       StudentReturnDTO dto = new StudentReturnDTO();
       dto.setEmail(student.getEmail());
       dto.setName(student.getName());
       dto.setMobileNumber(student.getMobileNumber());
       dto.setEnrollment(student.getEnrollment());
       dto.setGender(student.getGender());
       dto.setAddressLine1(student.getAddressLine1());
       dto.setAddressLine2(student.getAddressLine2());
       dto.setFatherName(student.getFatherName());

       return dto;
    }

    @Override
    @ApiOperation("Update Details of existing Student")
    public MessageResponse updateStudent(UpdateStudentDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);

        if(student == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

        if(dto.getMobileNumber() == null)
            student.setMobileNumber(student.getMobileNumber());
        student.setMobileNumber(dto.getMobileNumber());

        if(dto.getEmail() == null)
            student.setEmail(student.getEmail());
        student.setEmail(dto.getEmail());

        if(dto.getAddressLine2() == null)
            student.setAddressLine2(student.getAddressLine2());
        student.setAddressLine2(dto.getAddressLine2());

        if(dto.getAddressLine1() == null)
            student.setAddressLine1(student.getAddressLine1());
        student.setAddressLine1(dto.getAddressLine1());

        studentRepository.save(student);

        return MessageResponse.builder().message("Student Updated Successfully!").build();
    }




}
