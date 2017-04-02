package ru.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystemsverificationwork.database.dao.GenericDao;
import ru.tsystemsverificationwork.database.models.ClientAddress;



@Component("clientAddressDao")
public class ClientAddressDao extends GenericDao<ClientAddress> {

    public ClientAddressDao(){
        setClazz(ClientAddress.class);
    }
}
