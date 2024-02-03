package com.example.studentsmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class CourseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false,updatable = false)
    private String courseCode;
    private String courseName;
    private String faculty;
    private String headFaculty;
    @Column(nullable = false,updatable = false)
    private String studentCode;

}
