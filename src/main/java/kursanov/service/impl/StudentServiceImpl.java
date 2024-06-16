package kursanov.service.impl;

import jakarta.transaction.Transactional;
import kursanov.entities.Student;
import kursanov.repo.GroupRepo;
import kursanov.repo.StudentRepo;
import kursanov.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;




    @Override
    public void saveStudent(Long groupId, Student student) {
        studentRepo.saveStudent(groupId,student);
    }

    @Override
    public List<Student> getAllStudents(Long groupId) {
        return studentRepo.getAllStudents(groupId);
    }

    @Override
    public Student findByStudentId(Long groupId, Long studentId) {
        return studentRepo.findByStudentId(groupId,studentId);
    }

    @Override
    public Student updateStudent(Long groupId, Long studentId, Student student) {
        return studentRepo.updateStudent(groupId,studentId,student);
    }

    @Override
    @Transactional
    public void delete(Long groupId, Long studentId) {
        studentRepo.delete(groupId,studentId);

    }



}
