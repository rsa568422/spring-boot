package udemy.springboot.jwt.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.springboot.jwt.app.models.services.CustomerService;
import udemy.springboot.jwt.app.views.xml.CustomerList;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public CustomerList list() {
        return new CustomerList(customerService.findAll());
    }
}
