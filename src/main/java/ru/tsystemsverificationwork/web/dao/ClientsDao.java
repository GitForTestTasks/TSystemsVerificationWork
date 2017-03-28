package ru.tsystemsverificationwork.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.Role;

import java.util.List;


@Transactional
@Component("clientsDao")
public class ClientsDao extends GenericDao<Client> {

//    @Autowired
//    private RolesDao rolesDao;

    public ClientsDao(){
        setClazz(Client.class);
    }

    public boolean emailExists(String emailSubmitted) {

        return transactionManager.createQuery("FROM Client AS r WHERE r.email = :emailSubmitted", Client.class).
                setParameter("emailSubmitted", emailSubmitted).getResultList().size() > 0;
    }

    public Client getUserByEmail(String email) {

        return transactionManager.createQuery("FROM Client AS r WHERE r.email = :email", Client.class).
                setParameter("email", email).getSingleResult();
    }

}
