package udemy.springboot.form.app.services;

import org.springframework.stereotype.Service;
import udemy.springboot.form.app.models.entities.Role;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final List<Role> roles;

    public RoleServiceImpl() {
        this.roles = Arrays.asList(
                new Role(1, "ROLE_ADMIN", "Administrador"),
                new Role(2, "ROLE_USER", "Usuario"),
                new Role(3, "ROLE_MODERATOR", "Moderador")
        );
    }

    @Override
    public List<Role> list() {
        return roles;
    }

    @Override
    public Role findById(Integer id) {
        return roles.stream().filter(role -> role.getId().equals(id)).findFirst().orElse(null);
    }
}
