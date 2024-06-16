package kursanov.repo;

import kursanov.entities.Company;

import java.util.List;

public interface CompanyRepo {


    void saveCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company updateCompany(Long id,Company company);

    String delete(Long id);


}
