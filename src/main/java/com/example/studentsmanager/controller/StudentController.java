package com.example.studentsmanager.controller;


import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.findAllStudents();
        // Clear the enrollments list for each student
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
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student) {
        StudentDTO newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
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

