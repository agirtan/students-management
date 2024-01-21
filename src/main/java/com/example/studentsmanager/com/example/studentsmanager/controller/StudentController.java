package com.example.studentsmanager.com.example.studentsmanager.controller;

import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentModel>>getAllStudents(){
        List<StudentModel>students=studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<StudentModel>getStudentById(@PathVariable("id")Long id){
        StudentModel student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
