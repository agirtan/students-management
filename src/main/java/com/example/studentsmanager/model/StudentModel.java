package com.example.studentsmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel implements Serializable {

    @Id
    @Column(nullable = true, updatable = false)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_name"))

    @JsonManagedReference
    private Set<CourseModel> courses;



    @PrePersist
    public void generateStudentId() {
        Random random = new Random();
        int generatedCode = random.nextInt(9000) + 1000;
        this.id = (long) generatedCode;

    }
}
