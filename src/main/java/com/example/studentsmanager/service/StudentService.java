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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DTOConverter dtoConverter;

    @Autowired
    public StudentService (StudentRepository studentRepository,DTOConverter dtoConverter){
        this.studentRepository=studentRepository;
        this.dtoConverter=dtoConverter;
    }
    //ADD STUDENT
    public StudentDTO addStudent(StudentDTO studentDTO) {
        StudentModel studentModel = dtoConverter.convertToStudentEntity(studentDTO);
        StudentModel savedStudent = studentRepository.save(studentModel);
        return dtoConverter.convertToStudentDTO(savedStudent);
    }
    //FIND ALL
    public List<StudentDTO> findAllStudents() {
        List<StudentModel> students = studentRepository.findAll();
        return students.stream()
                .map(dtoConverter::convertToStudentDTO)
                .collect(Collectors.toList());
    }
    //FIND BY ID

    public StudentDTO findStudentById(Long id){
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(()-> new UserNotFound("Student with the provided id not found"));
        return dtoConverter.convertToStudentDTO(studentModel);
    }
    //UPDATE
    public StudentDTO updateStudent(Long studentId, StudentDTO updateStudent) {
        StudentModel existingStudent = studentRepository.findById(studentId)
                .orElseThrow(()-> new UserNotFound("Student with the provided id not found"));

        existingStudent.setName(updateStudent.getName());
        existingStudent.setStudentCode(updateStudent.getStudentCode());
        existingStudent.setEmail(updateStudent.getEmail());
        StudentModel updatedStudent = studentRepository.save(existingStudent);
        return dtoConverter.convertToStudentDTO(updatedStudent);
    }

    //DELETE
    public void deleteStudent(Long studentId){
        StudentModel studentModel = studentRepository.findById(studentId)
                .orElseThrow(()-> new UserNotFound("Student with the provided id not found"));
        studentRepository.deleteById(studentId);
    }
}
