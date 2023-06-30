package udemy.springboot.datajpa.app.models.daos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udemy.springboot.datajpa.app.models.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        return em.createQuery("from Customer").getResultList();
    }
}
