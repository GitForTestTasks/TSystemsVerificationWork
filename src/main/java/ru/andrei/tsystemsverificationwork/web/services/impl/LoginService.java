package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for login business logic
 */
@Service("loginService")
@Transactional
public class LoginService implements UserDetailsService {

    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    /**
     * Dao of clients entity
     */
    private ClientsDao clientsDao;

    @Autowired
    public LoginService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    /**
     * Returns user details by submitted e-mail
     *
     * @param email e-mail used as username
     * @return user details
     * @throws UsernameNotFoundException exception if not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) {

        Client user = clientsDao.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        log.info("User " + user.getEmail() + " has logged in");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), true, true
                , true, true, simpleGrantedAuthorities);
    }

    /**
     * Returns list of authorities granted to user
     *
     * @param client user logged in
     * @return list of authorities objects
     */
    private List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Client client) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (client.getRoles() != null) {
            for (Role role : client.getRoles()) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        return simpleGrantedAuthorities;
    }

}
