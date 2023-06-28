package udemy.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import udemy.springboot.form.app.models.entities.User;
import udemy.springboot.form.app.validations.UserValidation;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class FormController {

    private final UserValidation userValidation;

    @Autowired
    public FormController(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "Form usuario");
        model.addAttribute("user", new User(
                "123.456.789-K",
                null,
                null,
                null,
                "John",
                "Doe"
        ));
        return "form";
    }

    @PostMapping("/form")
    public String process(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, SessionStatus status) {
        userValidation.validate(user, result);
        model.addAttribute("title", "Form Resultado");
        if (result.hasErrors()) {
            return "form";
        }
        model.addAttribute("user", user);
        status.setComplete();
        return "result";
    }
}
