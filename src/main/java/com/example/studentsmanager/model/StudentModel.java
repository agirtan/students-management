package com.example.studentsmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.*;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class StudentModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long studentCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) // Adjust cascade as needed
    private List<EnrollmentModel> enrollments;

}

