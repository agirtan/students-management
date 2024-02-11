package com.example.studentsmanager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseDTO> courses; // Add this field to hold courses

    public StudentDTO(Long id, String name, String email) {
    }

    // Assuming CourseDTO is defined elsewhere and includes at least courseName and courseCode
}
