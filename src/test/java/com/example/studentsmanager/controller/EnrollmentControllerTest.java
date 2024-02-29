package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.DTOs.EnrollmentRequest;
import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(controllers = EnrollmentControllerTest.class, excludeAutoConfiguration = {
        HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
class EnrollmentControllerTest {

    @MockBean
    private EnrollmentService enrollmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testEnrollStudentSuccess() throws Exception {
        // Test data setup
        EnrollmentRequest request = new EnrollmentRequest(1L, List.of(2L, 3L));
        EnrollmentDTO enrollmentDTO1 = new EnrollmentDTO();
        enrollmentDTO1.setId(1L);

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setId(10L);
        studentDTO1.setName("Alexandru Pop");

        enrollmentDTO1.setStudent(studentDTO1);

        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setId(5L);
        courseDTO1.setCourseName("Italiana");

        enrollmentDTO1.setCourse(courseDTO1);


        EnrollmentDTO enrollmentDTO2 = new EnrollmentDTO();
        enrollmentDTO2.setId(2L);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setId(15L);
        studentDTO2.setName("Iancu Dragos");

        enrollmentDTO2.setStudent(studentDTO2);

        CourseDTO courseDTO2 = new CourseDTO();
        courseDTO2.setId(8L);
        courseDTO2.setCourseName("Franceza");

        enrollmentDTO2.setCourse(courseDTO2);

        // Mock service behavior for each course ID
        when(enrollmentService.enrollStudent(eq(1L), eq(2L))).thenReturn(enrollmentDTO1);
        when(enrollmentService.enrollStudent(eq(1L), eq(3L))).thenReturn(enrollmentDTO2);

        // Perform the request and assertions
        mockMvc.perform(MockMvcRequestBuilders.post("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(
                        objectMapper.writeValueAsString(List.of(enrollmentDTO1, enrollmentDTO2)),
                        result.getResponse().getContentAsString()));
    }
}
