package com.adnan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class StudentFee extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer perSemesterFee;//from course

    private Integer totalFee;//from course i.e. (perSemFee*Semesters)+otherCharges

    private Integer totalSubmittedFee; //sum of submitted fees up to now

    private Integer currentSemSubmittedFee; //submittedFees

    private Integer currentSemester; //semInfo

    private Integer balance;

    private String remarks;

    private Integer lateFeeFine;



}
