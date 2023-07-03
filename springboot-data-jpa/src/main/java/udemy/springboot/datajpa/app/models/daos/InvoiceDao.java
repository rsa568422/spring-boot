package udemy.springboot.datajpa.app.models.daos;

import org.springframework.data.repository.CrudRepository;
import udemy.springboot.datajpa.app.models.entities.Invoice;

public interface InvoiceDao extends CrudRepository<Invoice, Long> {
}
