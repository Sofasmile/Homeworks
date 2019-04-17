package jpa;

import jpa.dao.implementation.DeveloperDaoImpl;
import jpa.entity.Developer;
import jpa.util.HibernateUtil;

import javax.persistence.EntityManager;

public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        Developer developer = new Developer();
        developer.setName("Ivan");
        developer.setAge(28);
        developer.setGender("male");
        developer.setSalary(2000.00);

        DeveloperDaoImpl developerDao = new DeveloperDaoImpl(entityManager);
        developerDao.insertDeveloper(developer);
        HibernateUtil.shutdown();
    }
}
