package udemy.springboot.datajpa.app.models.daos;

import org.springframework.stereotype.Repository;
import udemy.springboot.datajpa.app.models.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        return em.createQuery("from Customer").getResultList();
    }

    @Override
    public Customer findOne(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void save(Customer customer) {
        if (Objects.nonNull(customer.getId()) && customer.getId() > 0) em.merge(customer);
        else em.persist(customer);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
