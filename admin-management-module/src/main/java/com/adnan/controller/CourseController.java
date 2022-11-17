package com.adnan.controller;

import com.adnan.dto.CourseDTO;
import com.adnan.dto.MessageResponse;
import com.adnan.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
        ("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/createCourse")
    @ApiOperation("Create New Course")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse createCourse(@RequestBody CourseDTO courseDTO){
        return courseService.createCourse(courseDTO);
    }

}
