package com.example.studentsmanager.DTOs;

import com.example.studentsmanager.views.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class EnrollmentDTO {

    @JsonView(View.Public.class)
    private Long id;
    private StudentDTO student;
    private CourseDTO course;
}