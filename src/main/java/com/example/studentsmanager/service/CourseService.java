package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.exception.CourseNotFoundException;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DTOConverter dtoConverter;

    @Autowired
    public CourseService(CourseRepository courseRepository, DTOConverter dtoConverter) {
        this.courseRepository = courseRepository;
        this.dtoConverter = dtoConverter;
    }

    public List<CourseDTO> addCourses(List<CourseDTO> courseDTOs) {
        List<CourseModel> courseModels = courseDTOs.stream()
                .map(dtoConverter::convertToCourseEntity)
                .collect(Collectors.toList());
        List<CourseModel> savedCourses = courseRepository.saveAll(courseModels);
        return savedCourses.stream()
                .map(dtoConverter::convertToCourseDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> findAllCourses() {
        List<CourseModel> courses = courseRepository.findAll();
        return courses.stream()
                .map(dtoConverter::convertToCourseDTO)
                .collect(Collectors.toList());
    }


    public CourseDTO findCourseById(Long id) {
        CourseModel courseModel = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
        return dtoConverter.convertToCourseDTO(courseModel);
    }

    public void deleteCourse(Long courseId) {
        CourseModel courseModel=courseRepository.findById(courseId).
                orElseThrow(()->new CourseNotFoundException("Course with given id : "
                        + courseId + " does not exist"));
        courseRepository.deleteById(courseId);
    }

    public CourseDTO updateCourse(Long courseId, CourseDTO updateCourse) {
        CourseModel existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        // Map from the DTO to the existing entity
        existingCourse.setCourseName(updateCourse.getCourseName());
        // ... map other fields as needed

        CourseModel updatedCourse = courseRepository.save(existingCourse);
        return dtoConverter.convertToCourseDTO(updatedCourse);
    }
}
