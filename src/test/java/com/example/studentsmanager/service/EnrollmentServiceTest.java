package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.EnrollmentRepository;
import com.example.studentsmanager.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class EnrollmentServiceTest {
    @Mock
    EnrollmentRepository enrollmentRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    CourseRepository courseRepository;
    @Mock
    DTOConverter dtoConverter;
    @InjectMocks
    EnrollmentService enrollmentService;

    @Test
    public void testEnrollStudentSuccess() {
        // Setup
        Long studentId = 1L;
        Long courseId = 2L;
        StudentModel student = new StudentModel(studentId, "Test Student", "test@email.com", 12345L, null);
        CourseModel course = new CourseModel(courseId, "Test Course", null);
        EnrollmentModel enrollment = new EnrollmentModel(null, student, course);
        EnrollmentDTO expectedDTO = new EnrollmentDTO(/* ... set up expected DTO */);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        when(dtoConverter.convertToEnrollmentDTO(enrollment)).thenReturn(expectedDTO);

        // Execution
        EnrollmentDTO result = enrollmentService.enrollStudent(studentId, courseId);

        // Assertions
        verify(studentRepository).findById(studentId);
        verify(courseRepository).findById(courseId);
        verify(enrollmentRepository).save(enrollment);
        verify(dtoConverter).convertToEnrollmentDTO(enrollment);

        assertEquals(expectedDTO, result);
    }
}

