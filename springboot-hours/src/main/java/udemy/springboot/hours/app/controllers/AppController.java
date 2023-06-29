package udemy.springboot.hours.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("${config.hours.open}")
    private Integer open;

    @Value("${config.hours.close}")
    private Integer close;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Bienvenido al horario de atención a clientes");
        return "index";
    }

    @GetMapping("/close")
    public String close(Model model) {
        String message = String.format("Cerrado, por favor visítenos entre las %d y las %d. Gracias.", open, close);
        model.addAttribute("title", "Fuera del horario de atención");
        model.addAttribute("message", message);
        return "close";
    }
}
