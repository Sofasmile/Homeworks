package jpa.dao;

import jpa.entity.Skill;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class SkillDao {
    private EntityManager entityManager;

    public SkillDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(skill);
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

    public boolean updateSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(skill);
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

    public Skill readSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            Skill skill = entityManager.find(Skill.class, id);
            entityManager.getTransaction().commit();
            return skill;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean deleteSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Skill.class, id));
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
