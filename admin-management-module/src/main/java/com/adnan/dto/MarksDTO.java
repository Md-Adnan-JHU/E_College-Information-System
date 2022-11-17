package com.adnan.dto;

import lombok.Data;

@Data
public class MarksDTO {

    private Long studentId;

    private Long subjectId;

    private Double assignmentSessionalObtainedMarks ;

    private Double semesterObtainedMarks;

    private Integer totalMaxMarks;

    private Integer semester;


}
