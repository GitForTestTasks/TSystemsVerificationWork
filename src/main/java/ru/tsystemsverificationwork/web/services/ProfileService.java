package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.dao.ClientAddressDao;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.ClientAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("profileService")
//@Transactional
public class ProfileService {


    private ClientAddressDao clientAddressDao;
    private ClientsDao clientsDao;

    @Autowired
    public ProfileService(ClientAddressDao clientAddressDao, ClientsDao clientsDao) {
        this.clientAddressDao = clientAddressDao;
        this.clientsDao = clientsDao;
    }


    public Client getCurrentUser() {

        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientsDao.getUserByEmail(userDetail.getUsername());
    }

    @Transactional
    public boolean updateInformation(Client client) {

        if (client == null) {
            return false;
        }

        Client comparingClient = getCurrentUser();
        client.setRoles(comparingClient.getRoles());

        client.setClientId(comparingClient.getClientId());
        clientsDao.update(client);

        return true;
    }

    @Transactional
    public Set<ClientAddress> getClientAddresses() {

        Set<ClientAddress> clientAddress = getCurrentUser().getClientAddresses();

        if (clientAddress == null) {
            return new HashSet<>();
        } else return clientAddress;
    }

    @Transactional
    public ClientAddress getCalledClientAddress(Long clientAddressId) {

        if (verificateRequestedAddress(clientAddressId)) {
            return clientAddressDao.findOne(clientAddressId);
        } else
            return new ClientAddress();
    }

    private boolean verificateRequestedAddress(Long clientAddressId) {

        if (clientAddressId == null || getCurrentUser().getClientAddresses() == null) {
            return false;
        }
        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(clientAddressId);

        return getCurrentUser().getClientAddresses().contains(clientAddress);
    }


    @Transactional
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
