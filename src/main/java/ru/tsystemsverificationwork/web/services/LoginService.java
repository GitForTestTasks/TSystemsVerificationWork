package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrei on 3/28/2017.
 */
@Service("loginService")
public class LoginService implements UserDetailsService {

    private ClientsDao clientsDao;

    @Autowired
    public LoginService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Logger log = Logger.getLogger(UserDetailsService.class.getName());
        log.log(Level.WARNING,  email + "allo");

        Client user = clientsDao.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.isEnabled(), true
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