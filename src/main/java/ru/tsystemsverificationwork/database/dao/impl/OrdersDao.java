package ru.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystemsverificationwork.database.dao.GenericDao;
import ru.tsystemsverificationwork.database.models.Order;
import ru.tsystemsverificationwork.web.services.impl.OrdersService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component("ordersDao")
public class OrdersDao extends GenericDao<Order> {

    public OrdersDao() {
        setClazz(Order.class);
    }

    public Order findOrder(Long clientId) {

        Logger log = Logger.getLogger(OrdersService.class.getName());
        log.log(Level.WARNING,  clientId.toString() + " clientId" );

        return transactionManager.createQuery("FROM Order AS r WHERE r.clientId.clientId = :clientId " +
                "ORDER BY r.dateOfCreation DESC", Order.class)
                .setParameter("clientId", clientId).setFirstResult(0).setMaxResults(1).getSingleResult();
    }

    public List<Order> getOrdersOfUser(Long clientId) {
        return transactionManager.createQuery("FROM Order AS r WHERE r.clientId.clientId = :clientId " +
                "ORDER BY r.dateOfCreation DESC", Order.class)
                .setParameter("clientId", clientId).getResultList();
    }

    public Long size() {

        return (Long) transactionManager.createQuery("select count(OrderId) from Order").getSingleResult();
    }

    public List<Order> getPagedOrders(int startingValue, int quantityResults) {

        return transactionManager.createQuery("FROM Order AS r ORDER BY r.dateOfCreation DESC", Order.class)
                .setFirstResult(startingValue).setMaxResults(quantityResults).getResultList();
    }
}
