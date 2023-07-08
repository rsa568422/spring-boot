package udemy.springboot.datajpa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {
        if (Objects.nonNull(principal)) {
            flash.addFlashAttribute("info", "Ya hay una sesión activa");
            return "redirect:/";
        }
        if (Objects.nonNull(error)) {
            model.addAttribute("error", "nombre de usuario o contraseña incorrectos");
        }
        if (Objects.nonNull(logout)) {
            model.addAttribute("success", "sesión cerrada con éxito");
        }
        return "login";
    }
}
