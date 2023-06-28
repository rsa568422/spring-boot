package udemy.springboot.form.app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import udemy.springboot.form.app.models.entities.User;

@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.user.name");
        if (!user.getIdentifier().matches("\\d{2}[.]\\d{3}[.]\\d{3}-[A-Z]")) {
            errors.rejectValue("identifier", "pattern.user.identifier");
        }
    }
}
