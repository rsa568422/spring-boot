package udemy.springboot.datajpa.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.services.CustomerService;
import udemy.springboot.datajpa.app.utils.paginators.PageRender;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Controller
@SessionAttributes("customer")
public class CustomerController {

    private final CustomerService service;

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Customer customer = service.findOne(id);
        if (Objects.isNull(customer)) {
            flash.addFlashAttribute("error", "el cliente no existe");
            return "redirect:/list";
        }
        model.addAttribute("title", String.format("Detalles del cliente: %s", customer.getName()));
        model.addAttribute("customer", customer);
        return "view";
    }

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Customer> customers = service.findAll(pageRequest);
        PageRender<Customer> pageRender = new PageRender<>("/list", customers);
        model.addAttribute("title", "Listado de clientes");
        model.addAttribute("customers", customers);
        model.addAttribute("page", pageRender);
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
    public String save(@Valid Customer customer, BindingResult result, Model model,
                       @RequestParam("file") MultipartFile photo, RedirectAttributes flash,
                       SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Formulario de cliente");
            return "form";
        }
        if (!photo.isEmpty()) {
            String fileName = String.format("%s_%s", UUID.randomUUID(), photo.getOriginalFilename());
            Path rootPath = Paths.get("uploads").resolve(fileName);
            log.info(rootPath.toAbsolutePath().toString());
            try {
                Files.copy(photo.getInputStream(), rootPath.toAbsolutePath());
                flash.addFlashAttribute("info", String.format("Ha subido correctamente: '%s'", fileName));
                customer.setPhoto(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
