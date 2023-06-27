package udemy.springboot.di.app.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class Client {

    @Value("&{client.name}")
    private String name;

    @Value("&{client.surname}")
    private String surname;
}
