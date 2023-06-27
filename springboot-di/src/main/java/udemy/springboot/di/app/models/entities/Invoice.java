package udemy.springboot.di.app.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Getter
@Setter
@Component
public class Invoice {

    @Value("${invoice.description}")
    private String description;

    @Autowired
    private Client client;

    @Autowired
    @Qualifier("officeInvoiceItems")
    private List<InvoiceItem> items;

    @PostConstruct
    public void init() {
        client.setName(String.format("%s Carlos", client.getName()));
        description = String.format("%s del cliente: %s", description, client.getName());
    }

    @PreDestroy
    public void destroy() {
        System.out.printf("Destroyed invoice: %s%n", description);
    }
}
