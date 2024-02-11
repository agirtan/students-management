package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentModel addStudent(StudentModel student) {

        return studentRepository.save(student);
    }

    public List<StudentModel> findAllStudents() {

        return studentRepository.findAll();
    }



    public StudentModel updateStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    @Transactional(readOnly = true)
    public StudentDTO findStudentById(Long id) {
        StudentModel student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        Hibernate.initialize(student.getCourses());
        return DTOConverter.convertToStudentDTO(student); // Direct method call
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentById(id);
    }
}
