package udemy.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentifierRegexValidator implements ConstraintValidator<IdentifierRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("\\d{2}[.]\\d{3}[.]\\d{3}-[A-Z]");
    }
}
