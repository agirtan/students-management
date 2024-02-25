package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired // Constructor injection is preferred
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.findAllCourses();
        return ResponseEntity.ok(courses); // Shorthand, same as HttpStatus.OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.findCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO addedCourse = courseService.addCourse(courseDTO);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);

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
        return ResponseEntity.noContent().build(); // 204 No Content is suitable for deletions
    }
}

