package com.adnan.service;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.StudentReturnDTO;
import com.adnan.dto.UpdateStudentDTO;

public interface StudentService {

    StudentReturnDTO getStudent(Long studentId);

    MessageResponse updateStudent(UpdateStudentDTO dto);

}
