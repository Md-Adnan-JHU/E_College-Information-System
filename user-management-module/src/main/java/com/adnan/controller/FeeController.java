package com.adnan.controller;

import com.adnan.dto.FeeReturnDTO;
import com.adnan.dto.FeeSubmitDTO;
import com.adnan.dto.MessageResponse;
import com.adnan.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
        ("/fee")
public class FeeController {

    @Autowired
    private StudentFeeService studentFeeService;



    @GetMapping("/getFeeDetails/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or ('ROLE_USER')")
    public FeeReturnDTO getStudentFeeDetails(@PathVariable Long studentId) {
        return studentFeeService.getStudentFeeDetails(studentId);
    }

    @PostMapping("/firstSubmit")
    public MessageResponse submitFeeFirstTime(@RequestBody FeeSubmitDTO submitDTO) {
        return studentFeeService.submitFee(submitDTO);
    }

    @PutMapping("/submit")
    public MessageResponse submitStudentFee(@RequestBody FeeSubmitDTO submitDTO) {
        return studentFeeService.submitStudentFee(submitDTO);
    }
}
