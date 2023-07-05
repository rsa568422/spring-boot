package udemy.springboot.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udemy.springboot.datajpa.app.models.entities.Customer;
import udemy.springboot.datajpa.app.models.services.CustomerService;
import udemy.springboot.datajpa.app.models.services.UploadFileService;
import udemy.springboot.datajpa.app.utils.paginators.PageRender;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

@Controller
@SessionAttributes("customer")
public class CustomerController {

    private final CustomerService customerService;

    private final UploadFileService uploadFileService;

    @Autowired
    public CustomerController(CustomerService customerService, UploadFileService uploadFileService) {
        this.customerService = customerService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable("filename") String fileName) {
        Resource resource = null;
        try {
            resource = uploadFileService.load(fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Customer customer = customerService.fetchByIdWithInvoices(id);
        if (Objects.isNull(customer)) {
            flash.addFlashAttribute(ERROR, "el cliente no existe");
            return REDIRECT_TO_LIST;
        }
        model.addAttribute(TITLE, String.format("Detalles del cliente: %s", customer.getName()));
        model.addAttribute(CUSTOMER, customer);
        return "view";
    }

    @GetMapping({"/", "/list"})
    public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Customer> customers = customerService.findAll(pageRequest);
        PageRender<Customer> pageRender = new PageRender<>("/list", customers);
        model.addAttribute(TITLE, "Listado de clientes");
        model.addAttribute("customers", customers);
        model.addAttribute("page", pageRender);
        return "list";
    }

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute(TITLE, "Formulario de cliente");
        model.addAttribute(CUSTOMER, new Customer());
        return "form";
    }

    @GetMapping("/form/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Customer customer;
        if (id > 0) {
            customer = customerService.findOne(id);
            if (Objects.isNull(customer)) {
                flash.addFlashAttribute(ERROR, "El id del cliente no existe");
                return REDIRECT_TO_LIST;
            }
        } else {
            flash.addFlashAttribute(ERROR, "El id del cliente no puede ser cero");
            return REDIRECT_TO_LIST;
        }
        model.addAttribute(TITLE, "Editar cliente");
        model.addAttribute(CUSTOMER, customer);
        return "form";
    }

    @PostMapping("/form")
    public String save(@Valid Customer customer, BindingResult result, Model model,
                       @RequestParam("file") MultipartFile photo, RedirectAttributes flash,
                       SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute(TITLE, "Formulario de cliente");
            return "form";
        }
        if (!photo.isEmpty()) {
            if (Objects.nonNull(customer.getId()) && customer.getId() > 0 &&
                    Objects.nonNull(customer.getPhoto()) && customer.getPhoto().length() > 0) {
                uploadFileService.delete(customer.getPhoto());
            }
            String fileName = null;
            try {
                fileName = uploadFileService.copy(photo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            flash.addFlashAttribute("info", String.format("Ha subido correctamente: '%s'", fileName));
            customer.setPhoto(fileName);
        }
        String message = customer.getId() == null
                ? "Cliente creado con éxito"
                : "Cliente modificado con éxito";
        customerService.save(customer);
        status.setComplete();
        flash.addFlashAttribute("success", message);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Customer customer = customerService.findOne(id);

            if (uploadFileService.delete(customer.getPhoto())) {
                flash.addFlashAttribute("info", String.format("Foto '%s' eliminada con éxito", customer.getPhoto()));
            }
            customerService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return REDIRECT_TO_LIST;
    }

    private static final String ERROR = "error";
    private static final String TITLE = "title";
    private static final String CUSTOMER = "customer";
    private static final String REDIRECT_TO_LIST = "redirect:/list";
}
