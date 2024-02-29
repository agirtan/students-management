package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.findAllCourses();
        courses.forEach(course->course.setEnrollments(null));
        return ResponseEntity.ok(courses); // Shorthand, same as HttpStatus.OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.findCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<List<CourseDTO>> addCourses(@RequestBody List<CourseDTO> courseDTOs) {
        List<CourseDTO> savedCourses = courseService.addCourses(courseDTOs);
        return new ResponseEntity<>(savedCourses, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Long courseId,
                                                  @RequestBody CourseDTO updatedCourse) {
        CourseDTO courseDTO = courseService.updateCourse(courseId, updatedCourse);
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

