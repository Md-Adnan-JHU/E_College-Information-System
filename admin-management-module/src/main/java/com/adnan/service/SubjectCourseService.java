package com.adnan.service;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.SubjectDTO;

public interface SubjectCourseService {


    MessageResponse createSubject(SubjectDTO dto);

    MessageResponse addExistingSubject(Long subjectId, Long courseId);
}
