package udemy.springboot.di.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Invoice {

    @Value("&{invoice.description}")
    private String description;

    @Autowired
    private Client client;

    private List<InvoiceItem> items;
}
