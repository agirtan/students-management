package com.example.studentsmanager.service;

import com.example.studentsmanager.exception.UserNotFound;
import com.example.studentsmanager.model.StudentModel;
import com.example.studentsmanager.repository.CourseRepository;
import com.example.studentsmanager.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

//    public void enrollStudent(Long id, EnrollmentRequest request) {
//        StudentModel student = studentRepository.findStudentById(id)
//                .orElseThrow(() -> new UserNotFound("Student not found with the id:" + id));
//
//        CourseModel course = courseRepository.findCourseByCourseName(request.courseName())
//                .orElseThrow(() -> new CourseNotFoundException("Course not found with the name: " + request.courseName()));
//
//        if (!student.getCourses().contains(course)) {
//            student.getCourses().add(course);  // Enroll the student in the course
//            studentRepository.save(student);
//        } else {
//            // Handle case where student is already enrolled
//            throw new EnrollmentException("Student is already enrolled in the course.");
//        }
//    }

    public StudentModel updateStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    public StudentModel findStudentById(Long id) {
        return studentRepository.findStudentById(id)
                .orElseThrow(() -> new UserNotFound("User by id " + id + " was not found!"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentById(id);
    }
}
