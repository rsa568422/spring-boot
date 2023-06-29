package udemy.springboot.hours.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Qualifier("hoursInterceptor")
    private final HandlerInterceptor hoursInterceptor;

    @Autowired
    public MvcConfig(HandlerInterceptor hoursInterceptor) {
        this.hoursInterceptor = hoursInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hoursInterceptor).excludePathPatterns("/close");
    }
}
