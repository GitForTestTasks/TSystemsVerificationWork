package ru.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.tsystemsverificationwork.database.models.Client;
import ru.tsystemsverificationwork.database.models.ClientAddress;
import ru.tsystemsverificationwork.web.services.GenericService;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service("profileService")
@Transactional
public class ProfileService extends GenericService {


    private ClientAddressDao clientAddressDao;
    private ClientsDao clientsDao;

    @Autowired
    public ProfileService(ClientAddressDao clientAddressDao, ClientsDao clientsDao) {
        this.clientAddressDao = clientAddressDao;
        this.clientsDao = clientsDao;
    }


    public boolean updateInformation(Client client) {

        if (client == null) {
            return false;
        }

        Client comparingClient = getCurrentUser();
        client.setRoles(comparingClient.getRoles());

        client.setClientId(comparingClient.getClientId());
        client.setOrders(comparingClient.getOrders());

        clientsDao.update(client);

        return true;
    }

    public Set<ClientAddress> getClientAddresses() {

        Set<ClientAddress> clientAddress = getCurrentUser().getClientAddresses();

        if (clientAddress == null) {
            return new HashSet<>();
        } else return clientAddress;
    }

    public ClientAddress getCalledClientAddress(Long clientAddressId) {

        if (verificateRequestedAddress(clientAddressId)) {
            return clientAddressDao.findOne(clientAddressId);
        } else
            return new ClientAddress();
    }

    public void updateCliendAddress(ClientAddress clientAddress) {

        if (clientAddressDao.findOne(clientAddress.getClientAddressId()) == null) {

            clientAddress.setClientId(getCurrentUser());
            clientAddressDao.create(clientAddress);

        } else {
            if(verificateRequestedAddress(clientAddress.getClientAddressId())) {
                clientAddress.setClientId(getCurrentUser());
                clientAddressDao.update(clientAddress);
            }
        }
    }
}
