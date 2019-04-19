package jpa.dao;

import jpa.entity.Customer;

public interface CustomerDao {
    boolean insertCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    Customer getByIdCustomer(Long id);

    boolean deleteCustomer(Long id);
}
