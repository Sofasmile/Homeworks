package jpa.dao;

import jpa.entity.Project;

import javax.persistence.EntityManager;

public class ProjectDao {
    private EntityManager entityManager;

    public ProjectDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertProject(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean updateProject(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Project readProject(Long id){
        try{
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.getTransaction().commit();
            return project;
        } catch (RuntimeException e){
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean deleteProject(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Project.class, id));
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
