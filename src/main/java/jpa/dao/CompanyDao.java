package jpa.dao;

import jpa.entity.Company;

public interface CompanyDao {
    boolean insertCompany(Company company);

    boolean updateCompany(Company company);

    Company getByIdCompany(Long id);

    boolean deleteCompany(Long id);
}
