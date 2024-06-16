package kursanov.service;

import kursanov.entities.Company;

import java.util.List;

public interface CompanyService {

    void saveCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company updateCompany(Long id,Company company);

    public void delete(Long id);
}
