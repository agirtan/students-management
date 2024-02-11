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
public class CourseDTO {

    private String courseName;
    private Long courseCode;
    private List<StudentDTO> students;

}
