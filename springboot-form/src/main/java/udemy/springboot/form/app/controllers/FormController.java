package udemy.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import udemy.springboot.form.app.editors.UpperCaseNameEditor;
import udemy.springboot.form.app.models.entities.User;
import udemy.springboot.form.app.validations.UserValidation;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("user")
public class FormController {

    private final UserValidation userValidation;

    @Autowired
    public FormController(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidation);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(format, true));
        binder.registerCustomEditor(String.class, "name", new UpperCaseNameEditor());
        binder.registerCustomEditor(String.class, "surname", new UpperCaseNameEditor());
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
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
