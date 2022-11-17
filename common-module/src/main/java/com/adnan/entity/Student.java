package com.adnan.entity;


import com.adnan.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Student extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_name")
    private String name;

    private String email;

    private Gender gender;

    private Long enrollment;

    private String addressLine1;

    private String addressLine2;

    private String fatherName;

    private Long mobileNumber;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Course course;

    @OneToOne
    private StudentFee studentFee;

}
