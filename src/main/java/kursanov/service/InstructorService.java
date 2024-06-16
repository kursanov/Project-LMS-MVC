package kursanov.service;

import kursanov.entities.Instructor;

import java.util.List;

public interface InstructorService {


    void saveInstructor(Instructor instructor);

    List<Instructor> getAllInstructors();

    void assign(Long companyId,Long instructorId);


    Instructor findByInstructorById(Long id);

    Instructor updateInstructor(Long id, Instructor instructor);

    String deleteInstructor(Long id);

    void detach(Long instructorId, Long companyId);
}
