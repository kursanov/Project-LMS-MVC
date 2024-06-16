package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Group;
import kursanov.entities.Student;
import kursanov.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {


    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void saveStudent(Long groupId, Student student) {
        Group group = entityManager.find(Group.class, groupId);
        if (group != null) {
            group.addStudent(student);
            student.setGroup(group);
            entityManager.persist(student);
        }
    }

    @Override
    public List<Student> getAllStudents(Long groupId) {
        entityManager.find(Group.class, groupId);
        return entityManager.createQuery("select s from Student s where  s.group.id = :groupId", Student.class)
                .setParameter("groupId", groupId)
                .getResultList();

    }

    @Override
    public Student findByStudentId(Long groupId, Long studentId) {
        return entityManager.createQuery("select s from Student s where s.group.id = :groupId and s.id = :studentId", Student.class)
                .setParameter("groupId", groupId)
                .setParameter("studentId", studentId).getSingleResult();

    }

    @Override
    @Transactional
    public Student updateStudent(Long groupId, Long studentId, Student student) {

        Student student1 = entityManager.find(Student.class, studentId);
        if (student1 == null || !student1.getGroup().getId().equals(groupId)) {
            throw new IllegalArgumentException("Student  found or does not belong to the specified group");
        }
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setFormatStudy(student.getFormatStudy());

        entityManager.merge(student1);
        return student1;

    }

    @Override
    @Transactional
    public void delete(Long groupId, Long studentId) {
        Group group = entityManager.find(Group.class, groupId);
        Student student = entityManager.find(Student.class, studentId);
        student.setGroup(null);
        group.getStudents().remove(student);
        entityManager.merge(group);
        entityManager.remove(student);
    }


}
