package udemy.springboot.di.app.models.services;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String operation() {
        return "ejecutando proceso de modelo de negocio";
    }
}
