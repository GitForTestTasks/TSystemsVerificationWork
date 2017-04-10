package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsClients;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("clientsDao")
@Transactional
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

    @SuppressWarnings("unchecked")
    public List<StatisticsClients> topTenClients() {

        List<Object[]> results = transactionManager.createNativeQuery("SELECT `clients`.`Email`, " +
                "`clients`.`FirstName`, `clients`.`LastName`, " +
                "SUM(`goods`.`Price`) AS `total` " +
                "FROM `clients` " +
                " LEFT JOIN `orders` ON `orders`.`ClientId` = `clients`.`ClientId` " +
                " LEFT JOIN `orderdetails` ON `orderdetails`.`OrderId` = `orders`.`OrderId` " +
                " LEFT JOIN `goods` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                " WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "GROUP BY `clients`.`ClientId` " +
                "HAVING `total` is not null limit 10;").getResultList();

        if (results == null)
            return new ArrayList<>();

        ArrayList<StatisticsClients> statisticsClientss = new ArrayList<>();

        results.forEach((record) -> {

            StatisticsClients statisticsClients = new StatisticsClients();
            statisticsClients.setEmail((String) record[0]);
            statisticsClients.setFirstName((String) record[1]);
            statisticsClients.setLastName((String) record[2]);
            statisticsClients.setTotal((BigDecimal) record[3]);

            statisticsClientss.add(statisticsClients);

        });

        return statisticsClientss;
    }

}
