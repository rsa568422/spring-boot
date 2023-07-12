package udemy.springboot.jwt.app.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.springboot.jwt.app.models.daos.UserDao;
import udemy.springboot.jwt.app.models.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    private static final Logger LOG = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Autowired
    public JpaUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            String message = String.format("No existe el usuario %s", username);
            LOG.error(message);
            throw new UsernameNotFoundException(message);
        }
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        authorities.stream().map(authority -> String.format("Role: %s", authority)).forEach(LOG::info);
        if (authorities.isEmpty()) {
            String message = String.format("El usuario %s no tiene roles asignados", username);
            LOG.error(message);
            throw new UsernameNotFoundException(message);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
