package udemy.springboot.error.app.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super(String.format("Usuario con id %s no existe en el sistema", id));
    }
}
