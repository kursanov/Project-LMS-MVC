package kursanov.service.impl;

import kursanov.entities.Company;
import kursanov.repo.CompanyRepo;
import kursanov.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepo companyRepo;
    @Override
    public void saveCompany(Company company) {
        companyRepo.saveCompany(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.getAllCompanies();
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepo.getCompanyById(id);
        if (company != null) {
            System.out.println("Company found: " + company.getName() + ", " + company.getCountry() + ", " + company.getAddress() + ", " + company.getPhoneNumber());
        } else {
            System.out.println("Company not found with name: " + id);
        }
        return company;
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        return companyRepo.updateCompany(id, company);
    }

    @Override
    public void delete(Long id) {
        companyRepo.delete(id);
    }
}
