package com.adnan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Course extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Integer semesters;

    private Integer perSemFee;

    private Integer otherCharges;

    @ManyToOne
    private Department department;

//    @ManyToOne
//    private StudentFee studentFee;





}
