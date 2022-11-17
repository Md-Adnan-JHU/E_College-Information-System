package com.adnan.controller;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.SubjectDTO;
import com.adnan.service.SubjectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
        ("/subject")
public class SubjectCourseController {

    @Autowired
    private SubjectCourseService subjectCourseService;

    @PostMapping("/createNew")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse createSubject(@RequestBody SubjectDTO dto){
        return subjectCourseService.createSubject(dto);
    }

    @PostMapping("/addExisting")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse addExistingSubject(@RequestParam Long subjectId, @RequestParam Long courseId) {
        return subjectCourseService.addExistingSubject(subjectId, courseId);
    }

}
