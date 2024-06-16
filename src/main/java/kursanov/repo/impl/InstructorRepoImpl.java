package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Instructor;
import kursanov.repo.InstructorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class InstructorRepoImpl implements InstructorRepo {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        String query = "select i from Instructor i where i.company is null";
        return entityManager.createQuery(query, Instructor.class).getResultList();
    }

    @Override
    @Transactional
    public void assign(Long companyId, Long instructorId) {
        Company company = entityManager.find(Company.class, companyId);
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        if (company != null && instructor != null){
            company.addInstructor(instructor);
            instructor.setCompany(company);
        }
    }

    @Override
    public Instructor findByInstructorById(Long id) {
        try {
            return  entityManager.createQuery("select i from Instructor i where i.id = :id", Instructor.class)
                    .setParameter("id",id)
                    .getSingleResult();

        }catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        return instructor1;
    }

    @Override
    public String deleteInstructor(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
        return "success";
    }

    @Override
    @Transactional
    public void detach(Long instructorId, Long companyId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Company company = entityManager.find(Company.class, companyId);
        if (instructor != null && company != null) {
            instructor.setCompany(null);
            company.getInstructors().remove(instructor);
        } else {
            throw new IllegalArgumentException("Instructor or company not found with ids: " + instructorId + ", " + companyId);
        }
    }

}
