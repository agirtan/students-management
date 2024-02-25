package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.EnrollmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<EnrollmentModel,Long> {

    List<EnrollmentModel> findByStudentIdAndCourseId(Long studentId, Long courseId);
}


