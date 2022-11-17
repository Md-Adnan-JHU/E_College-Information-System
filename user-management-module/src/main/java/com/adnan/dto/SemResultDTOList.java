package com.adnan.dto;

import lombok.Data;

import java.util.List;

@Data
public class SemResultDTOList {

    private String studentName;

    private Long enrollment;

//  private Double finalResult;

    private List<SemResultDTO> result;
}
