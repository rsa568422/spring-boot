package udemy.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import udemy.springboot.di.app.models.entities.InvoiceItem;
import udemy.springboot.di.app.models.entities.Product;
import udemy.springboot.di.app.models.services.IService;
import udemy.springboot.di.app.models.services.MyComplexService;
import udemy.springboot.di.app.models.services.MyService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("myService")
    public IService getMyService() {
        return new MyService();
    }

    @Primary
    @Bean("myComplexService")
    public IService getMyComplexService() {
        return new MyComplexService();
    }

    @Bean("invoiceItems")
    public List<InvoiceItem> getInvoiceItems() {
        Product product1 = new Product("Camara Sory", new BigDecimal("100"));
        Product product2 = new Product("Bicicleta Bianchi 26\"", new BigDecimal("200"));

        return Arrays.asList(
                new InvoiceItem(product1, 2),
                new InvoiceItem(product2, 4)
        );
    }
}
