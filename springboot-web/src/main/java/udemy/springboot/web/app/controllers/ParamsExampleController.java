package udemy.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/params")
public class ParamsExampleController {

    @GetMapping("/")
    public String index() {
        return "params/index";
    }

    @GetMapping("/string")
    public String param(@RequestParam(name = "text", required = false) String text, Model model) {
        model.addAttribute("result", String.format("Sent text is: %s", text));
        return "params/view";
    }

    @GetMapping("/mix-params")
    public String param(@RequestParam String text, @RequestParam Integer number, Model model) {
        model.addAttribute("result", String.format("Sent text is: %s | Sent number is: %d", text, number));
        return "params/view";
    }

    @GetMapping("/mix-params-request")
    public String param(HttpServletRequest request, Model model) {
        String text = request.getParameter("text");
        int number;
        try {
            number = Integer.parseInt(request.getParameter("number"));
        } catch (NumberFormatException e) {
            number = 0;
        }
        model.addAttribute("result", String.format("Sent text is: %s | Sent number is: %d", text, number));
        return "params/view";
    }
}
