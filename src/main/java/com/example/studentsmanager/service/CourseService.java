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
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseModel addCourse( CourseModel courseModel){
        // Assuming you want to set the same courseName, you can directly use it in the save method.
        return courseRepository.save(courseModel);
    }

    public List<CourseModel> findAllCourses(){
        return courseRepository.findAll();
    }

    public CourseModel updateCourse(CourseModel courseModel){
        return courseRepository.save(courseModel);
    }

    @Transactional(readOnly = true)
    public CourseDTO findCourseByName(String courseName) {
        CourseModel course = courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with name: " + courseName));
        // Force initialization of the students collection
        course.getStudents().size(); // This is fine if you are certain you're within a transactional context
        return DTOConverter.convertToCourseDTO(course);
    }
    public void deleteCourseByCourseName(String courseName) {
        courseRepository.deleteCourseByCourseName(courseName);
    }
}
