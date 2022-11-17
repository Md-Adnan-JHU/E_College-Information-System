package com.adnan.service;

import com.adnan.dto.MarksDTO;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.ResultDTO;
import com.adnan.dto.SemResultDTOList;

public interface ExamRecordService {
    MessageResponse addFinalMarks(MarksDTO dto);

    ResultDTO getFinalResult(Long studentId);

    SemResultDTOList getResult(Long studentId);
}
