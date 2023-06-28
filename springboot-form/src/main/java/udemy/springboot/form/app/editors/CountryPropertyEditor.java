package udemy.springboot.form.app.editors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import udemy.springboot.form.app.services.CountryService;

import java.beans.PropertyEditorSupport;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CountryService service;

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        try {
            setValue(service.findById(Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
