package com.example.studentsmanager.service;

import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
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

    public CourseModel findCourseByName(String courseName){
        return courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(()->new UserNotFound("Course "+ courseName + "was not found"));
    }

    public void deleteCourseByCourseName(String courseName) {
        courseRepository.deleteCourseByCourseName(courseName);
    }
}
