package udemy.springboot.datajpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import udemy.springboot.datajpa.app.authentication.handlers.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {

    private final LoginSuccessHandler successHandler;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurityConfig(LoginSuccessHandler successHandler, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.successHandler = successHandler;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService()throws Exception{

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build());
        manager.createUser(User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN","USER")
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/list**", "/locale").permitAll()
                /*.antMatchers("/view/**").hasAnyRole("USER")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("ADMIN")
                .antMatchers("/delete/**").hasAnyRole("ADMIN")
                .antMatchers("/invoice/**").hasAnyRole("ADMIN")*/
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successHandler).loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");

        return http.build();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}