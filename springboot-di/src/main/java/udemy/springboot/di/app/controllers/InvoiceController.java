package udemy.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udemy.springboot.di.app.models.entities.Invoice;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("title", "Ejemplo factura con inyección de dependencias");
        model.addAttribute("invoice", invoice);
        return "invoice/view";
    }
}
