package com.example.studentsmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Random;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="courses")
public class CourseModel {

    @Id
    @Column(nullable = false, updatable = true, unique = true)
    private String courseName;

    @Column(nullable = false,updatable = false)
    private Long courseCode;

    @ManyToMany(mappedBy="courses",fetch = FetchType.EAGER)

    private Set<StudentModel> students;


    @PrePersist
    public void generateCourseCode() {
        // Generate a 3-digit course code (you can customize this as needed)
        Random random = new Random();
        int generatedCode = random.nextInt(9000) + 1000; // Generates a random 3-digit number
        this.courseCode = (long) generatedCode;
    }

}
