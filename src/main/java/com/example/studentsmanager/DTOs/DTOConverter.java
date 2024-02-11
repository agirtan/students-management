package com.example.studentsmanager.DTOs;

import com.example.studentsmanager.model.CourseModel;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    public static CourseDTO convertToCourseDTO(CourseModel course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseName(course.getCourseName());
        dto.setCourseCode(course.getCourseCode());

        if (course.getStudents() != null && !course.getStudents().isEmpty()) {
            List<StudentDTO> studentDTOs = course.getStudents().stream()
                    .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail()))
                    .collect(Collectors.toList());
            dto.setStudents(studentDTOs);
        }

        return dto;
    }
}

