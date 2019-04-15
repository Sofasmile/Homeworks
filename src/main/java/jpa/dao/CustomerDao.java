package jpa.dao;

import jpa.entity.Customer;

import javax.persistence.EntityManager;

public class CustomerDao {
    private EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean updateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Customer readCustomer(Long id){
        try{
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e){
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean deleteCustomer(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
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
