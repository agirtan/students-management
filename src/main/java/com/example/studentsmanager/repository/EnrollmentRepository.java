package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.EnrollmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentModel,Long> {
}
