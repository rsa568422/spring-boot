package udemy.springboot.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.entities.Invoice;
import udemy.springboot.datajpa.app.models.entities.Product;
import udemy.springboot.datajpa.app.models.services.CustomerService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {

    private final CustomerService customerService;

    @Autowired
    public InvoiceController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/form/{customerId}")
    public String create(@PathVariable(value = "customerId") Long customerId, Model model, RedirectAttributes flash) {
        Customer customer = customerService.findOne(customerId);
        if (Objects.isNull(customer)) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/list";
        }
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        model.addAttribute("title", "Crear factura");
        model.addAttribute("invoice", invoice);
        return "invoices/form";
    }

    @GetMapping(value = "/load-products/{term}", produces = {"application/json"})
    public @ResponseBody List<Product> loadProducts(@PathVariable String term) {
        return customerService.findByName(term);
    }
}
