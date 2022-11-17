package com.adnan.serviceImpl;

import com.adnan.Repository.*;
import com.adnan.dto.FinalResultDTO;
import com.adnan.dto.ResultDTO;
import com.adnan.dto.SemResultDTO;
import com.adnan.dto.SemResultDTOList;
import com.adnan.entity.Student;
import com.adnan.entity.Student_Subject;
import com.adnan.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamRecordServiceImpl implements ExamRecordService {

   @Autowired
   private StudentRepository studentRepository;

   @Autowired
   private SubjectRepository subjectRepository;

   @Autowired
   private SubjectCourseRepo subjectCourseRepo;

   @Autowired
   private StudentSubjectRepository studentSubjectRepository;

   @Autowired
   private ExamRecordRepository examRecordRepository;



    @Override
    public ResultDTO getFinalResult(Long studentId){
        Student studentInfo = studentRepository.findById(studentId).orElse(null);
        if(studentInfo == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid StudentId!");

        List<Student_Subject> student_subjects = studentSubjectRepository.findByStudent(studentInfo);
        ArrayList<FinalResultDTO> resultList = new ArrayList<>();

        Integer semester = 0;
        for(Student_Subject s : student_subjects) {

            FinalResultDTO sem = new FinalResultDTO();
            sem.setPointsE(s.getExamRecord().getPointsEarned());
            sem.setCreditE(s.getExamRecord().getCreditEarned());
            semester = s.getExamRecord().getSemester();

            resultList.add(sem);
        }

        Integer pointSum = 0;
        Integer creditsEarnedSum = 0;

        for(FinalResultDTO f : resultList) {

            pointSum += f.getPointsE();
            creditsEarnedSum += f.getCreditE();

        }

        Double resultSum = (double)pointSum/creditsEarnedSum;

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setGrade(resultSum);
        resultDTO.setSem(semester);
        resultDTO.setStudentName(studentInfo.getName());


        return resultDTO;
    }

    @Override
    public SemResultDTOList getResult(Long studentId) {

        Student studentInfo = studentRepository.findById(studentId).orElse(null);
        if(studentInfo == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid StudentId!");

        List<Student_Subject> students = studentSubjectRepository.findByStudent(studentInfo);

        ArrayList<SemResultDTO> subjects = new ArrayList<>();

        for(Student_Subject s : students) {
            SemResultDTO sem = new SemResultDTO();

            sem.setGrade(s.getExamRecord().getGradePoint());
            sem.setSubjectName(s.getSubject().getName());
            sem.setSemester(s.getExamRecord().getSemester());


            subjects.add(sem);
        }

        SemResultDTOList dtoList = new SemResultDTOList();
        dtoList.setStudentName(studentInfo.getName());
        dtoList.setEnrollment(studentInfo.getEnrollment());
        dtoList.setResult(subjects);

        return dtoList;
    }



/*

    SGPA = EarnedCredits*GradePoints/CourseCredits
    CGPA = (Summation 1toM) EarnedCredits*GradePoints/CourseCredits

 */




}
