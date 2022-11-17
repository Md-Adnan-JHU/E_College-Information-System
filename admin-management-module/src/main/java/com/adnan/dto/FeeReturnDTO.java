package com.adnan.dto;

import lombok.Data;

@Data
public class FeeReturnDTO {

    private Integer currentSemFee;
    private Integer currentSemSubmittedFee;
    private Integer totalSubmittedFee;
    private Integer balance;
    private Integer fine;
    private String studentName;
    private String Course;
}
