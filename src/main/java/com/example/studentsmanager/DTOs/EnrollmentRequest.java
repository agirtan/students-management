package com.example.studentsmanager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentRequest {

    private Long studentId;
    private Long courseId;
}
