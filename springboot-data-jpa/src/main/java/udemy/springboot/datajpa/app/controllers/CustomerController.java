package udemy.springboot.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.springboot.datajpa.app.models.daos.CustomerDao;

@Controller
public class CustomerController {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "Listado de clientes");
        model.addAttribute("customers", customerDao.findAll());
        return "list";
    }
}
