package udemy.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import udemy.springboot.form.app.models.entities.User;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "Form usuario");
        return "form";
    }

    @PostMapping("/form")
    public String process(Model model,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email) {
        model.addAttribute("title", "Form Resultado");
        model.addAttribute("user", new User(username, password, email));
        return "result";
    }
}
