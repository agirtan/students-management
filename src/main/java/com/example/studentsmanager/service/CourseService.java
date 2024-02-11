package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseModel addCourse( CourseModel courseModel){
        // Assuming you want to set the same courseName, you can directly use it in the save method.
        return courseRepository.save(courseModel);
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findAllCourses() {
        List<CourseModel> courses = courseRepository.findAll();
        return courses.stream()
                .peek(course -> Hibernate.initialize(course.getStudents()))
                .map(DTOConverter::convertToCourseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDTO findCourseByName(String courseName) {
        CourseModel course = courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with name: " + courseName));
        Hibernate.initialize(course.getStudents());
        return DTOConverter.convertToCourseDTO(course);
    }
    
    
    public void deleteCourseByCourseName(String courseName) {
        courseRepository.deleteCourseByCourseName(courseName);
    }

    public CourseModel updateCourse(CourseModel course) {
        CourseModel existingCourse = courseRepository.findCourseByCourseName(course.getCourseName())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + course.getCourseName()));

        existingCourse.setCourseName(course.getCourseName());

        return courseRepository.save(existingCourse);

    }
}
