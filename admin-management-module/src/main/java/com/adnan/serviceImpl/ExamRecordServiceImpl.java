package com.adnan.serviceImpl;

import com.adnan.Repository.*;
import com.adnan.dto.*;
import com.adnan.entity.ExamRecord;
import com.adnan.entity.Student;
import com.adnan.entity.Student_Subject;
import com.adnan.entity.Subject;
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
    public MessageResponse addFinalMarks(MarksDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);
        Subject subject = subjectRepository.findById(dto.getSubjectId()).orElse(null);

        if(student == null || subject == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid StudentId or SubjectId!");

        ExamRecord record  = new ExamRecord();
        record.setSemester(dto.getSemester());
        record.setAssignmentSessionalObtainedMarks(dto.getAssignmentSessionalObtainedMarks());
        record.setTotalMaxMarks(dto.getTotalMaxMarks());
        record.setSemesterObtainedMarks(dto.getSemesterObtainedMarks());
        record.setTotalObtainedMarks(dto.getAssignmentSessionalObtainedMarks()+dto.getSemesterObtainedMarks());


        Double result = dto.getAssignmentSessionalObtainedMarks()+dto.getSemesterObtainedMarks();

//        Result AND Credits Logic
        if (result < 40) {
            record.setResult("Failed");
            record.setCreditEarned(0);
        }
        else {
            record.setResult("Passed");
            record.setCreditEarned(subject.getCredits());
        }

//        GradePoints AND PointsEarned Logic
        if(result >= 90) {
            record.setGradePoint(10);
            record.setPointsEarned(10 * subject.getCredits());
        } else if(result >= 80 && result <90) {
            record.setGradePoint(9);
            record.setPointsEarned(9 * subject.getCredits());
        } else if(result >= 70 && result <80) {
            record.setGradePoint(8);
            record.setPointsEarned(8 * subject.getCredits());
        } else if(result >= 65 && result <70) {
            record.setGradePoint(7);
            record.setPointsEarned(7 * subject.getCredits());
        } else if(result >= 55 && result <65) {
            record.setGradePoint(6);
            record.setPointsEarned(6 * subject.getCredits());
        } else if(result >= 50 && result <55) {
            record.setGradePoint(5);
            record.setPointsEarned(5 * subject.getCredits());
        } else if(result >= 40 && result <50) {
            record.setGradePoint(4);
            record.setPointsEarned(4 * subject.getCredits());
        }

        examRecordRepository.save(record);

        Student_Subject student_subject = new Student_Subject();
        student_subject.setStudent(student);
        student_subject.setSubject(subject);
        student_subject.setExamRecord(record);

        studentSubjectRepository.save(student_subject);

        return MessageResponse.builder().message("Marks Added Successfully!").build();
    }


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
