package com.example.studentsmanager.service;

import com.example.studentsmanager.DTOs.DTOConverter;
import com.example.studentsmanager.DTOs.StudentDTO;
import com.example.studentsmanager.exception.ResourceNotFoundException;
import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.CourseModel;
import com.example.studentsmanager.model.EnrollmentModel;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.EnrollmentRepository;
import com.example.studentsmanager.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


@Service

public class StudentService {

    private final StudentRepository studentRepository;
    private final DTOConverter dtoConverter;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    private static final int MAX_STUDENT_CODE = 999;
    private Random random = new Random();

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          DTOConverter dtoConverter,
                          CourseRepository courseRepository,
                          EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.dtoConverter = dtoConverter;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }
    //ADD STUDENT
    public StudentDTO addStudent(StudentDTO studentDTO) {
        // Generate a unique student code
        long studentCode;
        do {
            studentCode = 100 + random.nextInt(MAX_STUDENT_CODE - 100 + 1); // Generates a number from 100 to 999
        } while (studentRepository.existsByStudentCode(studentCode));

        StudentModel studentModel = dtoConverter.convertToStudentEntity(studentDTO);
        studentModel.setStudentCode(studentCode); // Set the unique code
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


    public boolean deleteStudentCourseEnrollment(Long studentId, Long courseId) {
        List<EnrollmentModel> enrollments = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollments.isEmpty()) {
            return false;
        }
        EnrollmentModel enrollment = enrollments.get(0);
        enrollmentRepository.delete(enrollment);
        return true;
    }
}
