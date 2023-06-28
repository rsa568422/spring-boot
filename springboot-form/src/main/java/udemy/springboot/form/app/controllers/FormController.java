package udemy.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import udemy.springboot.form.app.editors.CountryPropertyEditor;
import udemy.springboot.form.app.editors.UpperCaseNameEditor;
import udemy.springboot.form.app.models.entities.Country;
import udemy.springboot.form.app.models.entities.User;
import udemy.springboot.form.app.services.CountryService;
import udemy.springboot.form.app.validations.UserValidation;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("user")
public class FormController {

    private final UserValidation userValidation;

    private final CountryPropertyEditor countryPropertyEditor;

    private final CountryService service;

    @Autowired
    public FormController(UserValidation userValidation, CountryPropertyEditor countryPropertyEditor, CountryService service) {
        this.userValidation = userValidation;
        this.countryPropertyEditor = countryPropertyEditor;
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidation);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(format, true));
        binder.registerCustomEditor(String.class, "name", new UpperCaseNameEditor());
        binder.registerCustomEditor(String.class, "surname", new UpperCaseNameEditor());
        binder.registerCustomEditor(Country.class, "country", countryPropertyEditor);
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        return service.list().stream().map(Country::getName).collect(Collectors.toList());
    }

    @ModelAttribute("countriesMap")
    public Map<String, String> countriesMap() {
        return service.list()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getCode,
                        Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0).getName())
                ));
    }

    @ModelAttribute("countryList")
    public List<Country> countryList() {
        return service.list();
    }

    @ModelAttribute("roles")
    public List<String> roles() {
        return Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR");
    }

    @ModelAttribute("rolesMap")
    public Map<String, String> rolesMap() {
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "Form usuario");
        User user = new User(
                "12.456.789-K",
                "john",
                null,
                "rsa568422@gmail.com",
                5,
                null,
                null,
                null,
                "John",
                "Doe"
        );
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/form")
    public String process(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, SessionStatus status) {
        model.addAttribute("title", "Form Resultado");
        if (result.hasErrors()) {
            return "form";
        }
        model.addAttribute("user", user);
        status.setComplete();
        return "result";
    }
}
