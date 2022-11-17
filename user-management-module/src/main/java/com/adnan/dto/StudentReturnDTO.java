package com.adnan.dto;

import com.adnan.enums.Gender;
import lombok.Data;

@Data
public class StudentReturnDTO {

    private String name;

    private String email;

    private Gender gender;

    private Long enrollment;

    private String addressLine1;

    private String addressLine2;

    private String fatherName;

    private Long mobileNumber;


//    private Long courseId;

}
