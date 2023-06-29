package udemy.springboot.form.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private Integer id;

    private String code;

    private String name;

    @Override
    public String toString() {
        return id.toString();
    }
}
