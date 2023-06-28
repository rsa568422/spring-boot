package udemy.springboot.form.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udemy.springboot.form.app.validations.IdentifierRegex;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @IdentifierRegex
    private String identifier;

    @NotBlank
    @Size(min = 3, max = 8)
    private String username;

    @NotEmpty
    private String password;

    @Email(message = "correo con formato incorrecto")
    @NotEmpty
    private String email;

    private String name;

    @NotEmpty
    private String surname;
}
