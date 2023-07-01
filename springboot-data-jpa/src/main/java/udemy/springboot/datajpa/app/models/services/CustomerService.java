package udemy.springboot.datajpa.app.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import udemy.springboot.datajpa.app.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Customer findOne(Long id);

    void save(Customer customer);

    void delete(Long id);
}
