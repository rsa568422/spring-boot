package udemy.springboot.datajpa.app.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.entities.Invoice;
import udemy.springboot.datajpa.app.models.entities.Product;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Customer findOne(Long id);

    void save(Customer customer);

    void delete(Long id);

    List<Product> findByName(String term);

    void saveInvoice(Invoice invoice);

    Product findProductById(Long id);
}
