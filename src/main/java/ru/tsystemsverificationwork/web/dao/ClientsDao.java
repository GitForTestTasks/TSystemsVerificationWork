package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Client;


@Transactional
@Component("clientsDao")
public class ClientsDao extends GenericDao<Client> {

    public ClientsDao(){
        setClazz(Client.class);
    }

}
