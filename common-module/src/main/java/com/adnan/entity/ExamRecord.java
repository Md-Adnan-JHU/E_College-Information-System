package com.adnan.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ExamRecord extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double assignmentSessionalObtainedMarks ;

    private Double semesterObtainedMarks;

    private Integer totalMaxMarks;

    private Double totalObtainedMarks;

    private Integer GradePoint;

    private Integer creditEarned;

    private Integer pointsEarned;

    private String result;

    private Integer semester;



}
