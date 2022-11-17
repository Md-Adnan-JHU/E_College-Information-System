package com.adnan.serviceImpl;

import com.adnan.Repository.DepartmentRepository;
import com.adnan.dto.MessageResponse;
import com.adnan.entity.Department;
import com.adnan.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @ApiOperation("Create Department")
    public MessageResponse createDepartment(String deptName) {

        Department department = new Department();
        department.setName(deptName);

        departmentRepository.save(department);
        return MessageResponse.builder().message("Department Added Successfully!").build();
    }
}
