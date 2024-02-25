package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.CourseDTO;
import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.exception.CourseNotFoundException;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DTOConverter dtoConverter;

    @Autowired // Or preferably, use constructor injection
    public CourseService(CourseRepository courseRepository, DTOConverter dtoConverter) {
        this.courseRepository = courseRepository;
        this.dtoConverter = dtoConverter;
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        CourseModel courseModel = dtoConverter.convertToCourseEntity(courseDTO);
        CourseModel savedCourse = courseRepository.save(courseModel);
        return dtoConverter.convertToCourseDTO(savedCourse);
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
