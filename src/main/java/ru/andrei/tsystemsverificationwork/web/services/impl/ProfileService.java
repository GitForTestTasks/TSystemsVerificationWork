package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;

import java.util.HashSet;
import java.util.Set;


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

            throw new IllegalArgumentException();
//            return false;
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
            throw new ItemNotFoundException("Address is not found");
        } else return clientAddress;

    }

    public ClientAddress getCalledClientAddress(Long clientAddressId) {

        if (clientAddressId == null || clientAddressId < 1)
            throw new IllegalArgumentException();

        if (verificateRequestedAddress(clientAddressId)) {
            return clientAddressDao.findOne(clientAddressId);
        } else
            return new ClientAddress();
    }

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
    }
}
