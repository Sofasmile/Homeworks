package jpa.dao;

import jpa.entity.Company;

import javax.persistence.EntityManager;

public class CompanyDao {
    private EntityManager entityManager;

    public CompanyDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
            throw e;
        }
    }

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
            throw e;
        }
    }

    public Company readCompany(Long id){
        try{
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, id);
            entityManager.getTransaction().commit();
            return company;
        } catch (RuntimeException e){
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

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
            throw e;
        }
    }
}
