package ru.tsystemsverificationwork.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Client;

import java.util.List;

@Transactional
@Component("clientsDao")
public class ClientsDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        session().save(client);
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAllClients() {
        return session().createCriteria(Client.class).list();
    }

    public boolean exists(String client) {
        Criteria crit = session().createCriteria(Client.class);
        crit.add(Restrictions.idEq(client));
        Client user = (Client) crit.uniqueResult();
        return user != null;
    }
}
