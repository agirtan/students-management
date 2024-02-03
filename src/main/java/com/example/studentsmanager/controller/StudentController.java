package com.example.studentsmanager.controller;

import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentModel>>getAllStudents(){
        List<StudentModel>students=studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentModel>getStudentById(@PathVariable Long id){
        StudentModel student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/add")
    public  ResponseEntity<StudentModel> addStudent(@RequestBody StudentModel student){
        StudentModel newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping ("/update")
    public  ResponseEntity<StudentModel> updateStudent(@RequestBody StudentModel student){
        StudentModel updateStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<?> deleteStudent(@PathVariable("id")Long id){
         studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
