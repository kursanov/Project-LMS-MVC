package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class CompanyRepoImpl implements CompanyRepo {


    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCompany(Company company) {
        entityManager.persist(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        String query = ("select c from Company c");
        return entityManager.createQuery(query, Company.class).getResultList();

    }

    @Override
    public Company getCompanyById(Long id) {
        try {
            return entityManager.createQuery("select  c from Company c where c.id = :id", Company.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

    }
    }

    @Override
    @Transactional
    public Company updateCompany(Long id, Company company) {
        Company company1 = entityManager.find(Company.class, id);
        company1.setName(company.getName());
        company1.setCountry(company.getCountry());
        company1.setAddress(company.getAddress());
        company1.setPhoneNumber(company.getPhoneNumber());
        return company1;
    }

    @Transactional
    @Override
    public String delete(Long id) {
        entityManager.createQuery("DELETE FROM Company c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return "Success!";
    }
}
