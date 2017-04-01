package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.ClientAddress;


//@Transactional
@Component("clientAddressDao")
public class ClientAddressDao extends GenericDao<ClientAddress> {

    public ClientAddressDao(){
        setClazz(ClientAddress.class);
    }
}
