package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

/**
 * Business logic of profile
 */
@Service("profileService")
@Transactional
public class ProfileService extends GenericService {

    /**
     * Client addresses dao
     */
    private ClientAddressDao clientAddressDao;
    /**
     * Dao of users
     */
    private ClientsDao clientsDao;
    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    public ProfileService(ClientAddressDao clientAddressDao, ClientsDao clientsDao) {
        this.clientAddressDao = clientAddressDao;
        this.clientsDao = clientsDao;
    }

    /**
     * Updates profile information
     *
     * @param client client entity is going to be updated
     * @return boolean if there is no exceptions happen
     */
    public boolean updateInformation(Client client) {

        if (client == null) {

            throw new IllegalArgumentException();
        }

        Client comparingClient = getCurrentUser();
        client.setRoles(comparingClient.getRoles());
        client.setClientId(comparingClient.getClientId());
        client.setOrders(comparingClient.getOrders());
        clientsDao.update(client);
        log.info("Client " + getCurrentUser().getEmail() + " updated his information.");

        return true;
    }

    /**
     * Returns user's address by id.
     * Id should be verificated.
     *
     * @param clientAddressId id of address
     * @return address entity
     */
    public ClientAddress getCalledClientAddress(Long clientAddressId) {

        if (clientAddressId == null || clientAddressId < 1)
            throw new IllegalArgumentException();

        if (verificateRequestedAddress(clientAddressId)) {
            return clientAddressDao.findOne(clientAddressId);
        } else
            return new ClientAddress();
    }

    /**
     * Updates address
     *
     * @param clientAddress address entity is going to be updated
     */
    public void updateCliendAddress(ClientAddress clientAddress) {

        if (clientAddress == null)
            throw new IllegalArgumentException();

        if (clientAddressDao.findOne(clientAddress.getClientAddressId()) == null) {

            clientAddress.setClientId(getCurrentUser());
            clientAddressDao.create(clientAddress);

        } else {
            if (verificateRequestedAddress(clientAddress.getClientAddressId())) {
                clientAddress.setClientId(getCurrentUser());
                clientAddressDao.update(clientAddress);
            }
        }

        log.info("User " + getCurrentUser().getEmail() + " updated his address.");
    }
}
