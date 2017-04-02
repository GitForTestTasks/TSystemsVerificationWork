package ru.tsystemsverificationwork.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.GenericDao;
import ru.tsystemsverificationwork.database.models.Client;

@Component("clientsDao")
public class ClientsDao extends GenericDao<Client> {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(Client entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        super.create(entity);
    }

    @Override
    public void update(Client entity) {

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        transactionManager.find(Client.class, entity.getClientId());
        transactionManager.merge(entity);
    }

    public ClientsDao() {
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

    public void updateWithoutEncrypt(Client entity) {
        transactionManager.find(Client.class, entity.getClientId());
        transactionManager.merge(entity);
    }

}
