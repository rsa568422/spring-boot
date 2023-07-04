package udemy.springboot.datajpa.app.models.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import udemy.springboot.datajpa.app.models.entities.Customer;

public interface CustomerDao extends PagingAndSortingRepository<Customer, Long> {

    @Query("select c from Customer c left join fetch c.invoices i where c.id = ?1")
    Customer fetchByIdWithInvoices(Long id);
}
