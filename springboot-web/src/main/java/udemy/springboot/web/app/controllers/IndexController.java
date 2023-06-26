package udemy.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udemy.springboot.web.app.models.User;

@Controller
@RequestMapping("/app")
public class IndexController {

    @GetMapping({"/index", "/", "", "/home"})
    public String index(Model model) {
        model.addAttribute("title", "Hola spring framework");
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        User user = new User("Roberto", "Sánchez");
        model.addAttribute("title", String.format("Profile: %s, %s", user.getSurname(), user.getName()));
        model.addAttribute("user", user);
        return "profile";
    }
}
