package udemy.springboot.di.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
public class Invoice {

    @Value("&{invoice.description}")
    private String description;

    @Autowired
    private Client client;

    @Autowired
    private List<InvoiceItem> items;
}
