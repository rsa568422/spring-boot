package udemy.springboot.di.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.springboot.di.app.models.services.MyService;

@Controller
public class IndexController {

    private MyService service = new MyService();

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("result", service.operation());
        return "index";
    }
}
