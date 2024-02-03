package com.example.studentsmanager.repository;

import com.example.studentsmanager.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel,String> {

    void deleteCourseByName(String courseName);
    Optional<CourseModel> findCourseByName(String courseName);
}
