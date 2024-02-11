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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.findAllStudents() // Adjust this method to return List<StudentDTO>
                .stream()
                .map(DTOConverter::convertToStudentDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.findStudentById(id); // Make sure this method returns StudentDTO
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping()
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



//    @PostMapping("/enrollment/{id}")
//    public ResponseEntity<StudentModel> enrollStudent(@PathVariable("id") Long id, @RequestParam("course") String course) {
//        studentService.enrollStudent(id, course);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
