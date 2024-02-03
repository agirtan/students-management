package com.example.studentsmanager.service;

import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    public CourseModel addCourse(CourseModel courseModel){
        courseModel.setCourseCode(UUID.randomUUID().toString());
        return courseRepository.save(courseModel);
    }

    public List<CourseModel> findAllCourses(){
        return courseRepository.findAll();
    }

    public CourseModel updateCourse(CourseModel courseModel){
        return courseRepository.save(courseModel);
    }

    public CourseModel findCourseByName(String courseName){
        return courseRepository.findCourseByName(courseName)
                .orElseThrow(()->new UserNotFound("Course "+ courseName + "was not found"));
    }

    public void deleteCourseByName(String courseName) {
        courseRepository.deleteCourseByName(courseName) ;
    }
}
