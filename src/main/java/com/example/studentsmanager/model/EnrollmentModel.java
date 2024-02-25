package com.example.studentsmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="enrollments")
public class EnrollmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id") // Change to reference the StudentModel
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "course_id")  // Change to reference the CourseModel
    private CourseModel course;
}
