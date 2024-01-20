package com.example.studentsmanager.service;

import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentModel addStudent(StudentModel studentModel){
        studentModel.setStudentCode(UUID.randomUUID().toString());
        return studentRepository.save(studentModel);
    }
}
