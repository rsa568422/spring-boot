package udemy.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import udemy.springboot.error.app.model.entities.User;
import udemy.springboot.error.app.services.UserService;

@Controller
public class AppController {

    private final UserService service;

    @Autowired
    public AppController(UserService service) {
        this.service = service;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        Integer i = (100 / 0) * Integer.parseInt("jhgf");
        return "index";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        User user = service.findById(id);
        model.addAttribute("title", String.format("Detalles del usuario: %s", user.getName()));
        model.addAttribute("user", user);
        return "view";
    }
}
