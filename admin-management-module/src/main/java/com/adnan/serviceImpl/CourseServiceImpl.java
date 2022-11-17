package com.adnan.serviceImpl;

import com.adnan.Repository.CourseRepository;
import com.adnan.Repository.DepartmentRepository;
import com.adnan.dto.CourseDTO;
import com.adnan.dto.MessageResponse;
import com.adnan.entity.Course;
import com.adnan.entity.Department;
import com.adnan.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    @ApiOperation("Create Course")
    public MessageResponse createCourse(CourseDTO courseDTO) {

        Department department = departmentRepository.findByName(courseDTO.getDeptName());

        if(department == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department Id!");

        if(courseDTO.getCourseName().equalsIgnoreCase(courseRepository.findByName(courseDTO.getCourseName())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course Already Exists!");

        Course course = new Course();
        course.setName(courseDTO.getCourseName());
        course.setSemesters(courseDTO.getSemesters());
        course.setPerSemFee(courseDTO.getPerSemFee());
        course.setOtherCharges(courseDTO.getOtherCharges());
        course.setDepartment(department);

        courseRepository.save(course);

        return MessageResponse.builder().message("Course saved Successfully!").build();
    }



}
