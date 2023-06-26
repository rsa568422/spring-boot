package udemy.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.springboot.di.app.models.services.IService;

@Controller
public class IndexController {

    private IService service;

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("result", service.operation());
        return "index";
    }

    @Autowired
    @Qualifier("myService")
    public void setService(IService service) {
        this.service = service;
    }
}
