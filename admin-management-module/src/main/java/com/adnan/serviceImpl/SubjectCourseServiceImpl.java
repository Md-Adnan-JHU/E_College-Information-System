package com.adnan.serviceImpl;

import com.adnan.Repository.CourseRepository;
import com.adnan.Repository.SubjectCourseRepo;
import com.adnan.Repository.SubjectRepository;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.SubjectDTO;
import com.adnan.entity.Course;
import com.adnan.entity.Subject;
import com.adnan.entity.Subject_Course;
import com.adnan.service.SubjectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SubjectCourseServiceImpl implements SubjectCourseService {


    @Autowired
    private SubjectCourseRepo subjectCourseRepo;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public MessageResponse createSubject(SubjectDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);

        Subject subject = new Subject();
        subject.setCredits(dto.getCredits());
        subject.setName(dto.getName());
        subjectRepository.save(subject);

        Subject_Course subject_course = new Subject_Course();
        subject_course.setSubject(subject);
        subject_course.setCourse(course);
        subjectCourseRepo.save(subject_course);

        return MessageResponse.builder().message("Subject Saved Successfully").build();
    }

    @Override
    public MessageResponse addExistingSubject(Long subjectId, Long courseId) {

        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (subject == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Subject Id!");

        if (course == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Course Id!");

        Subject_Course subject_course = new Subject_Course();
        subject_course.setSubject(subject);
        subject_course.setCourse(course);

        return MessageResponse.builder().message("Subject Added Successfully").build();
    }


}
