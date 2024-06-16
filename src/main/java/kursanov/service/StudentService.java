package kursanov.service;

import kursanov.entities.Student;

import java.util.List;

public interface StudentService {


    void saveStudent(Long groupId,Student student);

    List<Student> getAllStudents(Long groupId);

    Student findByStudentId(Long groupId,Long studentId);

    Student updateStudent(Long groupId, Long studentId, Student student);

    void delete(Long groupId,Long studentId);

}
