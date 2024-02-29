package com.example.studentsmanager.controller;


import com.example.studentsmanager.DTOs.ResponsePayload;
import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.controller.util.ResponseBuilder;
import com.example.studentsmanager.exception.StudentAlreadyEnrolledException;
import com.example.studentsmanager.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.findAllStudents();

        students.forEach(student -> student.setEnrollments(null));
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.findStudentById(id);

        // Manually set the id of each enrollment to null to avoid serialization
        studentDTO.getEnrollments().forEach(enrollment -> enrollment.setId(null));

        return ResponseEntity.ok(studentDTO);
    }

    //ADD STUDENTS
    @PostMapping()
    public ResponseEntity<ResponsePayload> addStudent(@Valid @RequestBody StudentDTO student) {
        try {
          studentService.addStudent(student);
            return ResponseBuilder.buildResponsePayload("Student created!", HttpStatus.CREATED);
        } catch (StudentAlreadyEnrolledException e) {
            return ResponseBuilder.buildResponsePayload(String.format("Student already exists"),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long studentId,
                                                    @RequestBody StudentDTO updatedStudent) {
        StudentDTO studentDTO = studentService.updateStudent(studentId, updatedStudent);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<?> deleteStudentCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        boolean isDeleted = studentService.deleteStudentCourseEnrollment(studentId, courseId);
        if (isDeleted) {
            return ResponseEntity.ok().body("Course " + courseId + " successfully deleted from the student with id " + studentId + " . ");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found for student ID: " + studentId + " and course ID: " + courseId);
        }
    }
}

