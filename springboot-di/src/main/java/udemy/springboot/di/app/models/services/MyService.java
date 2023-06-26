package udemy.springboot.di.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("myService")
public class MyService implements IService {

    @Override
    public String operation() {
        return "ejecutando proceso de modelo de negocio";
    }
}
