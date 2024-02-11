package com.example.studentsmanager.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private String courseName;
    private Long courseCode;
    private List<StudentDTO> students;

    public CourseDTO(String courseName, Long courseCode) {
    }
}
