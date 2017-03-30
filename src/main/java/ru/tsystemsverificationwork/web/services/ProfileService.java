package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.dao.ClientAddressDao;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.ClientAddress;



@Service("profileService")
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

        Client comparingClient = getCurrentUser();
        client.setRoles(comparingClient.getRoles());

        client.setClientId(comparingClient.getClientId());
        clientsDao.update(client);

        return true;
    }

    public ClientAddress getClientAddress() {


        ClientAddress clientAddress = getCurrentUser().getClientAddressId();


        if (clientAddress == null) {
            return new ClientAddress();
        } else return clientAddress;
    }


    public void updateCliendAddress(ClientAddress clientAddress) {

        ClientAddress found = clientAddressDao.findOne(clientAddress.getClientAddressId());

        if (getCurrentUser().getClientAddressId() == null) {

            Client client = getCurrentUser();
            client.setClientAddressId(clientAddress);
            clientsDao.updateWithoutEncrypt(client);

        } else {

            Client client = getCurrentUser();
            clientAddress.setClientAddressId(getCurrentUser().getClientAddressId().getClientAddressId());
            client.setClientAddressId(clientAddress);
            clientsDao.updateWithoutEncrypt(client);
        }

    }
}
