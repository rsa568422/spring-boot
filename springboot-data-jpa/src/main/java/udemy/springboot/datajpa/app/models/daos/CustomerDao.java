package udemy.springboot.datajpa.app.models.daos;

import org.springframework.data.repository.PagingAndSortingRepository;
import udemy.springboot.datajpa.app.models.entities.Customer;

public interface CustomerDao extends PagingAndSortingRepository<Customer, Long> {

}
