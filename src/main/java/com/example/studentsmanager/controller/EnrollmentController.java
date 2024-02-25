package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.DTOs.EnrollmentRequest;
import com.example.studentsmanager.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;


    @PostMapping()
    public ResponseEntity<EnrollmentDTO> enrollStudent(@RequestBody EnrollmentRequest request) {
        Long studentId = request.getStudentId();
        Long courseId = request.getCourseId();

        EnrollmentDTO enrollmentDTO = enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.created(URI.create("/enrollments/" + enrollmentDTO.getId()))
                .body(enrollmentDTO);
    }

}