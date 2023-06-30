package udemy.springboot.datajpa.app.models.daos;

import udemy.springboot.datajpa.app.models.entities.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> findAll();
}
