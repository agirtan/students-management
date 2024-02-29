package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.EnrollmentDTO;
import com.example.studentsmanager.DTOs.EnrollmentRequest;
import com.example.studentsmanager.DTOs.EnrollmentUpdateRequest;
import com.example.studentsmanager.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    //ADD
@PostMapping()
public ResponseEntity<List<EnrollmentDTO>> enrollStudent(@RequestBody EnrollmentRequest request) {
    Long studentId = request.getStudentId();
    List<Long> courseIds = request.getCourseIds();

    List<EnrollmentDTO> enrollments = courseIds.stream()
            .map(courseId -> enrollmentService.enrollStudent(studentId, courseId))
            .collect(Collectors.toList());

    return ResponseEntity.ok(enrollments);
}


    @PutMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(
            @PathVariable Long enrollmentId,
            @RequestBody EnrollmentUpdateRequest updateRequest) {
        EnrollmentDTO updatedEnrollment = enrollmentService.updateEnrollment(enrollmentId, updateRequest);
        return ResponseEntity.ok(updatedEnrollment);
    }


    @DeleteMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }

}