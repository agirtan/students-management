package com.example.studentsmanager.DTOs;

import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.model.StudentModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@NoArgsConstructor

public class DTOConverter {

    public CourseDTO convertToCourseDTO(CourseModel courseModel) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(courseModel.getId());
        courseDTO.setCourseName(courseModel.getCourseName());

        List<EnrollmentDTO> enrollmentDTOs = courseModel.getEnrollments()
                .stream()
                .map(enrollment -> {
                    EnrollmentDTO dto = new EnrollmentDTO();


                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setId(enrollment.getStudent().getId());
                    studentDTO.setName(enrollment.getStudent().getName());
                    studentDTO.setStudentCode(enrollment.getStudent().getStudentCode());

                    dto.setStudent(studentDTO);
                    return dto;
                })
                .collect(Collectors.toList());
        courseDTO.setEnrollments(enrollmentDTOs);

        return courseDTO;
    }


    public CourseModel convertToCourseEntity(CourseDTO courseDTO) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(courseDTO.getId());
        courseModel.setCourseName(courseDTO.getCourseName());

        List<EnrollmentModel> enrollments = Optional.ofNullable(courseDTO.getEnrollments()) // Wrap with Optional
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::convertToEnrollmentEntity)
                .collect(Collectors.toList());
        courseModel.setEnrollments(enrollments);

        return courseModel;
    }

    public StudentDTO convertToStudentDTO(StudentModel studentModel) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentModel.getId());
        studentDTO.setName(studentModel.getName());
        studentDTO.setEmail(studentModel.getEmail());
        studentDTO.setStudentCode(studentModel.getStudentCode());

        List<EnrollmentDTO> enrollmentDTOs = studentModel.getEnrollments()
                .stream()
                .map(enrollment -> {
                    EnrollmentDTO dto = new EnrollmentDTO();
                    dto.setId(enrollment.getId());

                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setId(enrollment.getCourse().getId());
                    courseDTO.setCourseName(enrollment.getCourse().getCourseName());

                    dto.setCourse(courseDTO);
                    return dto;
                })
                .collect(Collectors.toList());
        studentDTO.setEnrollments(enrollmentDTOs);

        return studentDTO;
    }

    public StudentModel convertToStudentEntity(StudentDTO studentDTO) {
        StudentModel studentModel = new StudentModel();
        studentModel.setId(studentDTO.getId());
        studentModel.setName(studentDTO.getName());
        studentModel.setEmail(studentDTO.getEmail());
        studentModel.setStudentCode(studentDTO.getStudentCode());

        List<EnrollmentModel> enrollments = Optional.ofNullable(studentDTO.getEnrollments())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::convertToEnrollmentEntity)
                .collect(Collectors.toList());
        studentModel.setEnrollments(enrollments);

        return studentModel;
    }

    public EnrollmentDTO convertToEnrollmentDTO(EnrollmentModel enrollment) {
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setId(enrollment.getId());

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(enrollment.getStudent().getId());
        enrollmentDTO.setStudent(studentDTO);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(enrollment.getCourse().getId());
        enrollmentDTO.setCourse(courseDTO);

        return enrollmentDTO;
    }


    public EnrollmentModel convertToEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        EnrollmentModel enrollmentModel = new EnrollmentModel();
        enrollmentModel.setId(enrollmentDTO.getId());
        enrollmentModel.setStudent(convertToStudentEntity(enrollmentDTO.getStudent()));
        enrollmentModel.setCourse(convertToCourseEntity(enrollmentDTO.getCourse()));
        return enrollmentModel;
    }
}
