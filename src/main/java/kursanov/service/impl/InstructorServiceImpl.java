package kursanov.service.impl;

import kursanov.entities.Instructor;
import kursanov.repo.InstructorRepo;
import kursanov.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;
    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepo.saveInstructor(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.getAllInstructors();
    }

    @Override
    public void assign(Long companyId, Long instructorId) {
        instructorRepo.assign(companyId,instructorId);
    }

    @Override
    public Instructor findByInstructorById(Long id) {
        return instructorRepo.findByInstructorById(id);
    }


    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        return instructorRepo.updateInstructor(id,instructor);
    }

    @Override
    public String deleteInstructor(Long id) {
        return instructorRepo.deleteInstructor(id);
    }

    @Override
    public void detach(Long instructorId, Long companyId) {
       instructorRepo.detach(instructorId, companyId);
    }
}
