package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.StudentModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long> {

    boolean existsByStudentCode(Long studentCode);

   boolean existsByEmail(String studentEmail);
}
