package kursanov.repo;

import kursanov.entities.Student;

import java.util.List;

public interface StudentRepo {


    void saveStudent(Long groupId,Student student);

    List<Student> getAllStudents(Long groupId);

    Student findByStudentId(Long groupId,Long studentId);

    Student updateStudent(Long groupId, Long studentId, Student student);

    void delete(Long groupId,Long studentId);
}
