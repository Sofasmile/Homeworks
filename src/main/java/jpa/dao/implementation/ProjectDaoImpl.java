package jpa.dao.implementation;

import jpa.dao.ProjectDao;
import jpa.entity.Project;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class ProjectDaoImpl implements ProjectDao {
    private EntityManager entityManager;

    public ProjectDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
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
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
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
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Project getByIdProject(Long id) {
        try {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.getTransaction().commit();
            return project;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
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
            log.error(e.getMessage());
            return false;
        }
    }
}
