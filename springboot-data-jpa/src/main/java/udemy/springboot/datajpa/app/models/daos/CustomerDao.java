package udemy.springboot.datajpa.app.models.daos;

import org.springframework.data.repository.CrudRepository;
import udemy.springboot.datajpa.app.models.entities.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {

}
