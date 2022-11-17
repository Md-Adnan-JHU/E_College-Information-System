package com.adnan.dto;

import lombok.Data;

@Data
public class FeeSubmitDTO {


    private Long StudentId;
    private Integer submittedFee; //input -- stored in DB & used as payment amount input in payment gateway
    private Integer currentSemester;//input
    private Integer lateFeeFine;//input
    private String remarks; //input -- stored in DB & used as remarks input in payment gateway

}
