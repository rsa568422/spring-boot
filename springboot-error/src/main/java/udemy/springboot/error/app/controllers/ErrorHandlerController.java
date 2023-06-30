package udemy.springboot.error.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(ArithmeticException.class)
    public String arithmeticError(ArithmeticException e, Model model) {
        model.addAttribute("error", "Error de aritmética");
        model.addAttribute("message", e.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/generic";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatError(NumberFormatException e, Model model) {
        model.addAttribute("error", "Formato numérico inválido");
        model.addAttribute("message", e.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/generic";
    }
}
