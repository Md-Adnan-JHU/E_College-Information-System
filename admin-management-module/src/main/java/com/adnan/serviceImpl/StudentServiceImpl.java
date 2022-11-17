package com.adnan.serviceImpl;

import com.adnan.Repository.CourseRepository;
import com.adnan.Repository.DepartmentRepository;
import com.adnan.Repository.StudentFeeRepository;
import com.adnan.Repository.StudentRepository;
import com.adnan.dto.*;
import com.adnan.entity.Course;
import com.adnan.entity.Department;
import com.adnan.entity.Student;
import com.adnan.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    @ApiOperation("Create New Student")
    public MessageResponse createStudent(StudentDTO studentDTO) {

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAddressLine1(studentDTO.getAddressLine1());
        student.setAddressLine2(studentDTO.getAddressLine2());
        student.setEmail(studentDTO.getEmail());
        student.setEnrollment(studentDTO.getEnrollment());
        student.setFatherName(studentDTO.getFatherName());
        student.setGender(studentDTO.getGender());
        student.setMobileNumber(studentDTO.getMobileNumber());

        studentRepository.save(student);

        return MessageResponse.builder().message("Student Saved Successfully!").build();
    }

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
    @ApiOperation("Get a list of Details of Students")
    public StudentDetailedListDTO getStudents(Long courseId) {

        Course courses = courseRepository.findById(courseId).orElse(null);

        List<Student> students = studentRepository.findByCourse(courses);

        if (students == null || courses == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Student Found! Either CourseId is Invalid or List is Empty");

        List<StudentReturnDTO> studentReturnDTOList = new ArrayList<>();

        for (Student t : students) {
            StudentReturnDTO dto = new StudentReturnDTO();
            dto.setName(t.getName());
            dto.setEmail(t.getEmail());
            dto.setGender(t.getGender());
            dto.setAddressLine1(t.getAddressLine1());
            dto.setAddressLine2(t.getAddressLine2());
            dto.setEnrollment(t.getEnrollment());
            dto.setFatherName(t.getFatherName());
            dto.setMobileNumber(t.getMobileNumber());


            studentReturnDTOList.add(dto);
        }

        StudentDetailedListDTO dtoList = new StudentDetailedListDTO();
        dtoList.setStudents(studentReturnDTOList);

        return dtoList;

    }

    @Override
    public StudentListDTO getStudentsList(Long courseId) {
        Course courses = courseRepository.findById(courseId).orElse(null);

        List<Student> students = studentRepository.findByCourse(courses);

        if (students == null || courses == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Student Found! Either CourseId is Invalid or List is Empty");

        List<StudentListReturnDTO> studentReturnDTOList = new ArrayList<>();

        for (Student t : students) {
            StudentListReturnDTO dto = new StudentListReturnDTO();
            dto.setName(t.getName());
            dto.setEnrollment(t.getEnrollment());
            dto.setMobileNumber(t.getMobileNumber());

            studentReturnDTOList.add(dto);
        }

        StudentListDTO dtoList = new StudentListDTO();
        dtoList.setDto(studentReturnDTOList);

        return dtoList;
    }

    @Override
    @ApiOperation("Update/ADD Course of existing Student")
    public MessageResponse addCourse(AddCourseToStudentDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);

        if(course == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Course Id!");

        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);

        if(student == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

        student.setCourse(course);

        studentRepository.save(student);

        return MessageResponse.builder().message("Course Added Successfully!").build();
    }

    @Override
    @ApiOperation("Update/ADD Department of existing Student")
    public MessageResponse addDepartment(AddDeptToStudentDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);

        if(student == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

        Department department = departmentRepository.findById(dto.getDepartmentId()).orElse(null);

        if(department == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department Id!");

        student.setDepartment(department);

        studentRepository.save(student);
        return MessageResponse.builder().message("Department Added Successfully!").build();
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
