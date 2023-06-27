package udemy.springboot.di.app.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Component
@RequestScope
public class Client {

    @Value("${client.name}")
    private String name;

    @Value("${client.surname}")
    private String surname;
}
