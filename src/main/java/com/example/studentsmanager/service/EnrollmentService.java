package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.exception.CourseNotFoundException;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.EnrollmentRepository;
import com.example.studentsmanager.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private final DTOConverter dtoConverter;
    @Transactional
    public EnrollmentDTO enrollStudent(Long studentId, Long courseId) {
        StudentModel student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFound("Student not found with ID: " + studentId));
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));

        EnrollmentModel enrollment = new EnrollmentModel();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment = enrollmentRepository.save(enrollment);

        return dtoConverter.convertToEnrollmentDTO(enrollment);
    }
}