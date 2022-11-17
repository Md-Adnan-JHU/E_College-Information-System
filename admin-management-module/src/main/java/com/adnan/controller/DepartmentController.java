package com.adnan.controller;

import com.adnan.dto.MessageResponse;
import com.adnan.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
        ("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/createDepartment")
    @ApiOperation("Create New Department")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MessageResponse createDepartment(@RequestParam String deptName) {
        return departmentService.createDepartment(deptName);
    }


}
