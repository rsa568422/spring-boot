package udemy.springboot.di.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceItem {

    private Product product;

    private Integer quantity;
}
