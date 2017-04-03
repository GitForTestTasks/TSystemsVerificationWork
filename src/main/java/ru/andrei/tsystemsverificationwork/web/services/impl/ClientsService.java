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

    public void setClientsDao(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    public List<Client> getCurrent() {

        return clientsDao.getAll();
    }

    public void createAccount(Client client) {

        clientsDao.create(client);
    }

    public boolean clientExists(String email) {

        return clientsDao.emailExists(email);
    }
}