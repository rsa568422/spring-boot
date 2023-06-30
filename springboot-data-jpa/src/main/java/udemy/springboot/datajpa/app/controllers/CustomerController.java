package udemy.springboot.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import udemy.springboot.datajpa.app.models.daos.CustomerDao;
import udemy.springboot.datajpa.app.models.entities.Customer;

import javax.validation.Valid;

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

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute("title", "Formulario de cliente");
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @PostMapping("/form")
    public String save(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Formulario de cliente");
            return "form";
        }
        customerDao.save(customer);
        return "redirect:list";
    }
}
