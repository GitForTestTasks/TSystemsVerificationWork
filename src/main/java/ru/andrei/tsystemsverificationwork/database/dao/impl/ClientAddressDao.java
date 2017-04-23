package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;

/**
 * Dao object of ClientAddress entity
 */
@Component("clientAddressDao")
public class ClientAddressDao extends GenericDao<ClientAddress> {

    public ClientAddressDao() {
        setClazz(ClientAddress.class);
    }
}
