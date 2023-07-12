package udemy.springboot.jwt.app.models.daos;

import org.springframework.data.repository.CrudRepository;
import udemy.springboot.jwt.app.models.entities.User;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
