package udemy.springboot.form.app.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentifierRegexValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IdentifierRegex {
    String message() default "identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
