package udemy.springboot.datajpa.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.springboot.datajpa.app.models.daos.CustomerDao;
import udemy.springboot.datajpa.app.models.daos.InvoiceDao;
import udemy.springboot.datajpa.app.models.daos.ProductDao;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.entities.Invoice;
import udemy.springboot.datajpa.app.models.entities.Product;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    private final ProductDao productDao;

    private final InvoiceDao invoiceDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, ProductDao productDao, InvoiceDao invoiceDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
        this.invoiceDao = invoiceDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findOne(Long id) {
        return customerDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Product> findByName(String term) {
        return productDao.findByNameLikeIgnoreCase(String.format("%%%s%%", term));
    }

    @Override
    @Transactional
    public void saveInvoice(Invoice invoice) {
        invoiceDao.save(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findInvoiceById(Long id) {
        return invoiceDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteInvoice(Long id) {
        invoiceDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice fetchByIdWithCustomerWithInvoiceItemWithProduct(Long id) {
        return invoiceDao.fetchByIdWithCustomerWithInvoiceItemWithProduct(id);
    }
}
