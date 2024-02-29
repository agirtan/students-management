package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentRepositoryTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void testFindByStudentIdAndCourseId() {
        Long studentId = 1L;
        Long courseId = 2L;


        EnrollmentModel enrollment1 = new EnrollmentModel();
        EnrollmentModel enrollment2 = new EnrollmentModel();
        List<EnrollmentModel> expectedEnrollments = List.of(enrollment1, enrollment2);


        when(enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId))
                .thenReturn(expectedEnrollments);


        List<EnrollmentModel> actualEnrollments = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);


        assertEquals(expectedEnrollments, actualEnrollments);
        verify(enrollmentRepository, times(1)).findByStudentIdAndCourseId(studentId, courseId);
    }
}