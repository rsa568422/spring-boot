package udemy.springboot.error.app.services;

import udemy.springboot.error.app.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();

    Optional<User> findById(Integer id);
}
