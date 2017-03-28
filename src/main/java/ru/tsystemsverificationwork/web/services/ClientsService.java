package ru.tsystemsverificationwork.web.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;

import java.util.List;

@Service("clientsService")
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
