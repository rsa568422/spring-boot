package udemy.springboot.datajpa.app.models.services;

import udemy.springboot.datajpa.app.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findOne(Long id);

    void save(Customer customer);

    void delete(Long id);
}
