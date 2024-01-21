package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long> {
    void deleteStudentById(Long id);

    Optional<StudentModel> findStudentById(Long id);
}
