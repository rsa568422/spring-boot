package udemy.springboot.form.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udemy.springboot.form.app.validations.IdentifierRegex;
import udemy.springboot.form.app.validations.Required;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

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
    @Required
    private String email;

    @NotNull
    @Min(5)
    @Max(5000)
    private Integer account;

    @NotNull
    @Past
    private Date birthday;

    @Valid
    private Country country;

    private String name;

    @Required
    private String surname;
}
