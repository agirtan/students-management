package com.example.studentsmanager.DTOs;

import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.StudentModel;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public class DTOConverter {

    public static CourseDTO convertToCourseDTO(CourseModel course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseName(course.getCourseName());
        dto.setCourseCode(course.getCourseCode());

        List<StudentDTO> studentDTOs = Optional.ofNullable(course.getStudents())
                .orElse(Collections.emptySet()) // Handle null
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), null)) // Adjust constructor as necessary
                .collect(Collectors.toList());

        dto.setStudents(studentDTOs.isEmpty() ? null : studentDTOs); // Set to null or empty list based on your preference

        return dto;
    }

    public static StudentDTO convertToStudentDTO(StudentModel student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId()); // Assuming StudentDTO also has an id field
        dto.setName(student.getName());
        dto.setEmail(student.getEmail()); // Assuming StudentDTO also has an email field

        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            List<CourseDTO> courseDTOS = student.getCourses().stream()
                    .map(course -> new CourseDTO(course.getCourseName(), course.getCourseCode()))
                    .collect(Collectors.toList());
            dto.setCourses(courseDTOS); // Make sure StudentDTO has a setter for courses list
        }

        return dto;
    }
}

