package com.example.studentsmanager.controller;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")

    public ResponseEntity<List<StudentDTO>>getAllStudents(){
        List<StudentDTO> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.findStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
//ADD STUDENTS
    @PostMapping()
    public  ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student){
        StudentDTO newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public  ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long studentId,
                                                     @RequestBody StudentDTO updatedStudent){
        StudentDTO studentDTO = studentService.updateStudent(studentId,updatedStudent);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public  ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
