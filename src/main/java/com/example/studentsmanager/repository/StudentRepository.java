package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel,Long> {
}
