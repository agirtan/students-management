package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.DTOs.EnrollmentUpdateRequest;
import com.example.studentsmanager.exception.CourseNotFoundException;
import com.example.studentsmanager.exception.ResourceNotFoundException;
import com.example.studentsmanager.exception.StudentAlreadyEnrolledException;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.EnrollmentRepository;
import com.example.studentsmanager.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service

public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final DTOConverter dtoConverter;


   @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository, DTOConverter dtoConverter) {
       this.enrollmentRepository = enrollmentRepository;
       this.studentRepository = studentRepository;
       this.courseRepository = courseRepository;
       this.dtoConverter = dtoConverter;
    }


    @Transactional
    public EnrollmentDTO enrollStudent(Long studentId, Long courseId) {

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (alreadyEnrolled) {
            throw new StudentAlreadyEnrolledException("Student " + studentId + " is already enrolled in course " + courseId);
        }
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

    @Transactional
    public EnrollmentDTO updateEnrollment(Long enrollmentId, EnrollmentUpdateRequest updateRequest) {
        EnrollmentModel enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        // Check if the student and course are valid and perform the necessary updates
        // For example, you might want to change the course like this:
        enrollment.setCourse(courseRepository.getOne(updateRequest.getNewCourseId()));
        // Save the updated enrollment
        EnrollmentModel savedEnrollment = enrollmentRepository.save(enrollment);
        return dtoConverter.convertToEnrollmentDTO(savedEnrollment);
    }

    public void deleteEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Enrollment not found");
        }
        enrollmentRepository.deleteById(enrollmentId);
    }

}