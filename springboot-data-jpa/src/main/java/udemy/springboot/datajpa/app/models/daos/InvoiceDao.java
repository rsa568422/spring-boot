package udemy.springboot.datajpa.app.models.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import udemy.springboot.datajpa.app.models.entities.Invoice;

public interface InvoiceDao extends CrudRepository<Invoice, Long> {

    @Query("select i from Invoice i join fetch i.customer c join fetch i.items l join fetch l.product where i.id = ?1")
    Invoice fetchByIdWithCustomerWithInvoiceItemWithProduct(Long id);
}
