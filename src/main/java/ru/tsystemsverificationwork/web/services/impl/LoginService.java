package ru.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.tsystemsverificationwork.database.models.Client;
import ru.tsystemsverificationwork.database.models.Role;

import java.util.ArrayList;
import java.util.List;

@Service("loginService")
@Transactional
public class LoginService implements UserDetailsService {

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
