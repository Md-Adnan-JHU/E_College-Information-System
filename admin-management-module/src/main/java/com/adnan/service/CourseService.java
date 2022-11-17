package com.adnan.service;

import com.adnan.dto.CourseDTO;
import com.adnan.dto.MessageResponse;

public interface CourseService {
    MessageResponse createCourse(CourseDTO courseDTO);

}
