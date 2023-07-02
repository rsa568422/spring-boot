package udemy.springboot.datajpa.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import udemy.springboot.datajpa.app.utils.paginators.PageRender;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable("filename") String fileName) {
        Path photoPath = Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
        log.info("photoPath: {}", photoPath);
        Resource resource = null;
        try {
            resource = new UrlResource(photoPath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("No se puede cargar la imagen");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Customer customer = service.findOne(id);
        if (Objects.isNull(customer)) {
            flash.addFlashAttribute(ERROR, "el cliente no existe");
            return REDIRECT_TO_LIST;
        }
        model.addAttribute(TITLE, String.format("Detalles del cliente: %s", customer.getName()));
        model.addAttribute(CUSTOMER, customer);
        return "view";
    }

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Customer> customers = service.findAll(pageRequest);
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
            customer = service.findOne(id);
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
            Path uploads = Paths.get(UPLOADS_FOLDER);
            if (Objects.nonNull(customer.getId()) && customer.getId() > 0 &&
                    Objects.nonNull(customer.getPhoto()) && customer.getPhoto().length() > 0) {
                File file = uploads.resolve(customer.getPhoto()).toAbsolutePath().toFile();
                if (file.exists() && file.canRead()) {
                    file.delete();
                }
            }
            String fileName = String.format("%s_%s", UUID.randomUUID(), photo.getOriginalFilename());
            Path rootPath = uploads.resolve(fileName);
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
            Customer customer = service.findOne(id);
            File file = Paths.get(UPLOADS_FOLDER).resolve(customer.getPhoto()).toAbsolutePath().toFile();
            if (file.exists() && file.canRead() && file.delete()) {
                flash.addFlashAttribute("info", String.format("Foto '%s' eliminada con éxito", customer.getPhoto()));
            }
            service.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return REDIRECT_TO_LIST;
    }

    private static final String UPLOADS_FOLDER = "uploads";
    private static final String ERROR = "error";
    private static final String TITLE = "title";
    private static final String CUSTOMER = "customer";
    private static final String REDIRECT_TO_LIST = "redirect:/list";
}
