package udemy.springboot.jwt.app.views.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udemy.springboot.jwt.app.models.entities.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customers")
public class CustomerList {

    @XmlElement(name = "customer")
    private List<Customer> customers;
}
