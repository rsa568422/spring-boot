package udemy.springboot.di.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceItem {

    private Product product;

    private Integer quantity;

    public BigDecimal getTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.CEILING);
    }
}
