package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;

/**
 * Created by Andrei on 3/29/2017.
 */
@Service("profileService")
public class ProfileService {

    private ClientsDao clientsDao;

    @Autowired
    public ProfileService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    public Client getCurrentUser() {

        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientsDao.getUserByEmail(userDetail.getUsername());

        return client;
    }

    @Transactional
    public boolean updateInformation(Client client) {

        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client comparingClient = clientsDao.getUserByEmail(userDetail.getUsername());
        client.setRoles(comparingClient.getRoles());

        client.setClientId(comparingClient.getClientId());
        clientsDao.update(client);

        return true;
    }

}
