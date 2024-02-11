package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseModel>>getAllCourses(){
        List<CourseModel>courses=courseService.findAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{coursename}")
    public ResponseEntity<CourseDTO> getCourseByCourseName(@PathVariable String coursename) {
        // Directly receive CourseDTO from the service
        CourseDTO courseDTO = courseService.findCourseByName(coursename);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CourseModel> addCourse(@RequestBody  CourseModel courseModel) {
        CourseModel addedCourse = courseService.addCourse(courseModel);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CourseModel>updateCourse(@RequestBody CourseModel course){
        CourseModel updateCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(updateCourse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{coursename}")
    public ResponseEntity<?> deleteCourseById(@PathVariable String coursename){
        courseService.deleteCourseByCourseName(coursename);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
