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
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.Role;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;

import java.util.ArrayList;
import java.util.List;

@Service("loginService")
@Transactional
public class LoginService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private ClientsDao clientsDao;

    @Autowired
    public LoginService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Client user = clientsDao.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        log.info("User " + user.getEmail() + " has logged in");

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), true, true
                , true, true, simpleGrantedAuthorities);
        return userDetails;
    }


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
