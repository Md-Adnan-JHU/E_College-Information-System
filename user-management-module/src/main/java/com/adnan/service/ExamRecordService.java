package com.adnan.service;

import com.adnan.dto.ResultDTO;
import com.adnan.dto.SemResultDTOList;

public interface ExamRecordService {

    ResultDTO getFinalResult(Long studentId);

    SemResultDTOList getResult(Long studentId);
}
