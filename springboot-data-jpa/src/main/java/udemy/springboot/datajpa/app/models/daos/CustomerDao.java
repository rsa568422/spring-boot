package udemy.springboot.datajpa.app.models.daos;

import udemy.springboot.datajpa.app.models.entities.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    void save(Customer customer);

    Customer findOne(Long id);
}
