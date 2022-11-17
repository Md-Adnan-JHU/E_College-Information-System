package com.adnan.service;

import com.adnan.dto.FeeReturnDTO;
import com.adnan.dto.FeeSubmitDTO;
import com.adnan.dto.MessageResponse;

public interface StudentFeeService {
    FeeReturnDTO getStudentFeeDetails(Long studentId);

    MessageResponse submitFee(FeeSubmitDTO submitDTO);

    MessageResponse submitStudentFee(FeeSubmitDTO submitDTO);
}
