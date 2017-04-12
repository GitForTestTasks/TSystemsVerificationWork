package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import java.util.List;

@Service("clientsService")
@Transactional
public class ClientsService extends GenericService {

    private static final Logger log = LoggerFactory.getLogger(ClientsService.class);
    private ClientsDao clientsDao;

    @Autowired
    public ClientsService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    public void createAccount(Client client) {

        if (client == null)
            throw new IllegalArgumentException();

        log.info("Account " + client.getEmail() + " has been created");
        clientsDao.create(client);
    }

    public boolean clientExists(String email) {

        if (email == null || email.isEmpty())
            throw new IllegalArgumentException();

        return clientsDao.emailExists(email);
    }
}
