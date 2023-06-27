package udemy.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import udemy.springboot.di.app.models.services.IService;
import udemy.springboot.di.app.models.services.MyComplexService;
import udemy.springboot.di.app.models.services.MyService;

@Configuration
public class AppConfig {

    @Bean("myService")
    public IService getMyService() {
        return new MyService();
    }

    @Primary
    @Bean("myComplexService")
    public IService getMyComplexService() {
        return new MyComplexService();
    }
}
