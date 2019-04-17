package jpa.dao.implementation;

import jpa.dao.CompanyDao;
import jpa.entity.Company;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class CompanyDaoImpl implements CompanyDao {
    private EntityManager entityManager;

    public CompanyDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insertCompany(Company company) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(company);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCompany(Company company) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(company);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Company readCompany(Long id) {
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, id);
            entityManager.getTransaction().commit();
            return company;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Company.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return false;
        }
    }
}
