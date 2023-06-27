package udemy.springboot.di.app.models.services;

public class MyComplexService implements IService {

    @Override
    public String operation() {
        return "ejecutando proceso complejo de modelo de negocio";
    }
}
