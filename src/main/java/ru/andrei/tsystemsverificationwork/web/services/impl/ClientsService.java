package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;

/**
 * Service responsible for users
 */
@Service("clientsService")
@Transactional
public class ClientsService {

    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(ClientsService.class);
    /**
     * Dao of client entity
     */
    private ClientsDao clientsDao;

    @Autowired
    public ClientsService(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    /**
     * Creates an account
     *
     * @param client user is going to be created
     */
    public void createAccount(Client client) {

        if (client == null)
            throw new IllegalArgumentException();

        log.info("Account " + client.getEmail() + " has been created");
        clientsDao.create(client);
    }

    /**
     * Checks if user exists
     *
     * @param email e-mail of user to be found
     * @return boolean value of result
     */
    public boolean clientExists(String email) {

        if (email == null || email.isEmpty())
            throw new IllegalArgumentException();

        return clientsDao.emailExists(email);
    }
}
