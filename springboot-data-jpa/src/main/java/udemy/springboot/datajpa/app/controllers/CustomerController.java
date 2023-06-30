package udemy.springboot.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.services.CustomerService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@SessionAttributes("customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "Listado de clientes");
        model.addAttribute("customers", service.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute("title", "Formulario de cliente");
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @GetMapping("/form/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Customer customer;
        if (id > 0) {
            customer = service.findOne(id);
            if (Objects.isNull(customer)) {
                flash.addFlashAttribute("error", "El id del cliente no existe");
                return "redirect:/list";
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser cero");
            return "redirect:/list";
        }
        model.addAttribute("title", "Editar cliente");
        model.addAttribute("customer", customer);
        return "form";
    }

    @PostMapping("/form")
    public String save(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Formulario de cliente");
            return "form";
        }
        String message = customer.getId() == null
                ? "Cliente creado con éxito"
                : "Cliente modificado con éxito";
        service.save(customer);
        status.setComplete();
        flash.addFlashAttribute("success", message);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            service.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return "redirect:/list";
    }
}
