package udemy.springboot.error.app.services;

import udemy.springboot.error.app.model.entities.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findById(Integer id);
}
