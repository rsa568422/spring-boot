package udemy.springboot.datajpa.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.springboot.datajpa.app.models.daos.CustomerDao;
import udemy.springboot.datajpa.app.models.entities.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao dao;

    @Autowired
    public CustomerServiceImpl(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findOne(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        dao.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
