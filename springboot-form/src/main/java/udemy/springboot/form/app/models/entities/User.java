package udemy.springboot.form.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String identifier;

    @NotEmpty
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
