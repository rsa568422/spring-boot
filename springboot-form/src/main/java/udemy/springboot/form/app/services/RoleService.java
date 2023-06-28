package udemy.springboot.form.app.services;

import udemy.springboot.form.app.models.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> list();

    Role findById(Integer id);
}
