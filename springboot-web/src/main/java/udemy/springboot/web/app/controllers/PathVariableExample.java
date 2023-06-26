package udemy.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class PathVariableExample {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Sending path variables");
        return "variables/index";
    }

    @GetMapping("/string/{text}")
    public String variables(@PathVariable String text, Model model) {
        model.addAttribute("title", "Receiving path variables");
        model.addAttribute("result", String.format("Sent text is: %s", text));
        return "variables/view";
    }

    @GetMapping("/string/{text}/{number}")
    public String variables(@PathVariable String text, @PathVariable Integer number, Model model) {
        model.addAttribute("title", "Receiving path variables");
        model.addAttribute("result", String.format("Sent text is: %s | Sent number is: %d", text, number));
        return "variables/view";
    }
}
