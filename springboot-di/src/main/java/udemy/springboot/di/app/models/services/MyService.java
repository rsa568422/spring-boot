package udemy.springboot.di.app.models.services;

import org.springframework.stereotype.Service;

@Service("myService")
public class MyService implements IService {

    @Override
    public String operation() {
        return "ejecutando proceso de modelo de negocio";
    }
}
