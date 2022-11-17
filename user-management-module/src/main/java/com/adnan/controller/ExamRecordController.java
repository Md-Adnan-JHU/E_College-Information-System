package com.adnan.controller;

import com.adnan.dto.ResultDTO;
import com.adnan.dto.SemResultDTOList;
import com.adnan.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
        ("/marks")
public class ExamRecordController {


    @Autowired
    private ExamRecordService examRecordService;


    @PostMapping("/getResult/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public SemResultDTOList showResult(@RequestParam Long studentId){
        return examRecordService.getResult(studentId);
    }

    @PostMapping("/getFinalResult/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public ResultDTO getFinalResult(@RequestParam Long studentId){
        return examRecordService.getFinalResult(studentId);
    }




}
