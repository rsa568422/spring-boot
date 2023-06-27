package udemy.springboot.di.app.models.services;

public class MyService implements IService {

    @Override
    public String operation() {
        return "ejecutando proceso de modelo de negocio";
    }
}
