package com.example.studentsmanager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentUpdateRequest {

    private Long newStudentId;
    private Long newCourseId;
}

