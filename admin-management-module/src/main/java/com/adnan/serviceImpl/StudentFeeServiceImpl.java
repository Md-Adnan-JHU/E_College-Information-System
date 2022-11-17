package com.adnan.serviceImpl;


import com.adnan.Repository.StudentFeeRepository;
import com.adnan.Repository.StudentRepository;
import com.adnan.dto.FeeReturnDTO;
import com.adnan.dto.FeeSubmitDTO;
import com.adnan.dto.MessageResponse;
import com.adnan.entity.Student;
import com.adnan.entity.StudentFee;
import com.adnan.service.StudentFeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentFeeServiceImpl implements StudentFeeService {

    @Autowired
    private StudentFeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    @ApiOperation("Get Details of Student Fee submission")
    public FeeReturnDTO getStudentFeeDetails(Long studentId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if(student == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

        FeeReturnDTO dto = new FeeReturnDTO();
        dto.setStudentName(student.getName());
        dto.setCourse(student.getCourse().getName());
        dto.setCurrentSemSubmittedFee(student.getStudentFee().getCurrentSemSubmittedFee());
        dto.setTotalSubmittedFee(student.getStudentFee().getTotalSubmittedFee());
        dto.setFine(student.getStudentFee().getLateFeeFine());
        dto.setBalance(student.getStudentFee().getBalance());
        dto.setCurrentSemFee(student.getStudentFee().getPerSemesterFee());

        return dto;
    }

    @Override
    @ApiOperation("Submit Fee of Student for first submission only via Student Id")
    public MessageResponse submitFee(FeeSubmitDTO submitDTO) {

        Student student = studentRepository.findById(submitDTO.getStudentId()).orElse(null);

        if(student == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");

        StudentFee fee = new StudentFee();

        fee.setCurrentSemSubmittedFee(submitDTO.getSubmittedFee());
        fee.setTotalSubmittedFee(submitDTO.getSubmittedFee());
        fee.setLateFeeFine(submitDTO.getLateFeeFine());
        fee.setRemarks(submitDTO.getRemarks());
        fee.setCurrentSemester(submitDTO.getCurrentSemester());
        fee.setTotalFee((student.getCourse().getPerSemFee()*student.getCourse().getSemesters())+student.getCourse().getOtherCharges());
        fee.setPerSemesterFee(student.getCourse().getPerSemFee());
        fee.setBalance((student.getCourse().getPerSemFee()+student.getCourse().getOtherCharges())-submitDTO.getSubmittedFee());
        fee.setId(student.getId());
        feeRepository.save(fee);

        student.setStudentFee(fee);
        studentRepository.save(student);

        return MessageResponse.builder().message("Fee Submitted Successfully!").build();
    }

    @Override
    @ApiOperation("Submit Fee of Student after first submission via StudentId")
    public MessageResponse submitStudentFee(FeeSubmitDTO submitDTO) {

        StudentFee fee = feeRepository.findById(submitDTO.getStudentId()).orElse(null);

        if(fee == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Student Id!");


        fee.setTotalSubmittedFee(fee.getTotalSubmittedFee()+ submitDTO.getSubmittedFee());
        fee.setRemarks(submitDTO.getRemarks());
        fee.setCurrentSemester(submitDTO.getCurrentSemester());
        fee.setLateFeeFine(fee.getLateFeeFine()+ submitDTO.getLateFeeFine());
        fee.setCurrentSemSubmittedFee(submitDTO.getSubmittedFee());

        if(fee.getCurrentSemester() == submitDTO.getCurrentSemester())
            fee.setBalance(fee.getBalance()-submitDTO.getSubmittedFee());

        fee.setBalance(fee.getBalance()+(fee.getPerSemesterFee()-submitDTO.getSubmittedFee()));
        feeRepository.save(fee);


        return MessageResponse.builder().message("Fee Submission Updated Successfully!").build();
    }
}
