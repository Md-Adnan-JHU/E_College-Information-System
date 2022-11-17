package com.adnan.dto;

import lombok.Data;

@Data
public class UpdateStudentDTO {

    private Long studentId;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private Long mobileNumber;
}
