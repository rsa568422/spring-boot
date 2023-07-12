package udemy.springboot.jwt.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udemy.springboot.jwt.app.models.entities.Customer;
import udemy.springboot.jwt.app.models.entities.Invoice;
import udemy.springboot.jwt.app.models.entities.InvoiceItem;
import udemy.springboot.jwt.app.models.entities.Product;
import udemy.springboot.jwt.app.models.services.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {

    private final CustomerService customerService;

    @Autowired
    public InvoiceController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Invoice invoice = customerService.fetchByIdWithCustomerWithInvoiceItemWithProduct(id);
        if (Objects.isNull(invoice)) {
            flash.addFlashAttribute("error", "la factura no existe");
            return "redirect:/list";
        }
        model.addAttribute("title", String.format("Factura: %s", invoice.getDescription()));
        model.addAttribute("invoice", invoice);
        return "invoices/view";
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

    @PostMapping("/form")
    public String save(@Valid Invoice invoice, BindingResult result, Model model,
                       @RequestParam(name = "item_id[]", required = false) Long[] itemIds,
                       @RequestParam(name = "quantity[]", required = false) Integer[] quantities,
                       RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Crear factura");
            return "invoices/form";
        }
        if (Objects.isNull(itemIds) || itemIds.length == 0) {
            model.addAttribute("title", "Crear factura");
            model.addAttribute("error", "la factura debe contener alguna línea");
            return "invoices/form";
        }
        for (int i = 0; i < itemIds.length; i++) {
            Product product = customerService.findProductById(itemIds[i]);
            InvoiceItem item = new InvoiceItem();
            item.setQuantity(quantities[i]);
            item.setProduct(product);
            invoice.addItem(item);
            log.info("ID: {}, quantity: {}", itemIds[i], quantities[i]);
        }
        customerService.saveInvoice(invoice);
        status.setComplete();
        flash.addFlashAttribute("success", "Factura creada con éxito");
        return String.format("redirect:/view/%d", invoice.getCustomer().getId());
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes flash) {
        Invoice invoice = customerService.findInvoiceById(id);
        if (Objects.nonNull(invoice)) {
            customerService.deleteInvoice(id);
            flash.addFlashAttribute("success", "factura eliminada con éxito");
            return String.format("redirect:/view/%d", invoice.getCustomer().getId());
        }
        flash.addFlashAttribute("error", "la factura no existe, no se pudo eliminar");
        return "redirect:/list";
    }
}
