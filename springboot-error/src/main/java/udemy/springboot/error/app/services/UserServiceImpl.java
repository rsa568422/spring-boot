package udemy.springboot.error.app.services;

import org.springframework.stereotype.Service;
import udemy.springboot.error.app.model.entities.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Andrés", "Guzmán"),
            new User(2, "Pepa", "García"),
            new User(3, "Lalo", "Mena"),
            new User(4, "Luci", "Fernández"),
            new User(5, "Pato", "González"),
            new User(6, "Paco", "Rodríguez"),
            new User(7, "Juan", "Gómez")
    );

    @Override
    public List<User> list() {
        return USERS;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return USERS.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
