package udemy.springboot.form.app.editors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udemy.springboot.form.app.services.RoleService;

import java.beans.PropertyEditorSupport;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService service;

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        try {
            setValue(service.findById(Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
