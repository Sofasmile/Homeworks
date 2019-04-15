package jpa.dao;

import jpa.entity.Developer;

import javax.persistence.EntityManager;

public class DeveloperDao {
    private EntityManager entityManager;

    public DeveloperDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertDeveloper(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(developer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean updateDeveloper(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(developer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Developer readDeveloper(Long id){
        try{
            entityManager.getTransaction().begin();
            Developer developer = entityManager.find(Developer.class, id);
            entityManager.getTransaction().commit();
            return developer;
        } catch (RuntimeException e){
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean deleteDeveloper(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Developer.class, id));
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
