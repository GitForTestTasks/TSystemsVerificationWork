package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;

import java.util.List;

@Service("clientsService")
@Transactional
public class ClientsService {

    private ClientsDao clientsDao;

    @Autowired
    public ClientsService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    public void createAccount(Client client) {

        if (client == null)
            throw new IllegalArgumentException();

        clientsDao.create(client);
    }

    public boolean clientExists(String email) {

        if (email == null || email.isEmpty())
            throw new IllegalArgumentException();

        return clientsDao.emailExists(email);
    }
}
