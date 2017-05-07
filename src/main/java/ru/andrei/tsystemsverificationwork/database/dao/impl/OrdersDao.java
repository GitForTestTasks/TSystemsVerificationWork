package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Order;

import java.util.List;

/**
 * Dao object of Order entity
 */
@Component("ordersDao")
public class OrdersDao extends GenericDao<Order> {

    public OrdersDao() {
        setClazz(Order.class);
    }

    /**
     * Finds order by id
     *
     * @param clientId id to find order
     * @return Order object found
     */
    public Order findOrder(Long clientId) {
        return transactionManager.createQuery("FROM Order AS r WHERE r.clientId.clientId = :clientId " +
                "ORDER BY r.dateOfCreation DESC", Order.class)
                .setParameter("clientId", clientId).setFirstResult(0).setMaxResults(1).getSingleResult();
    }

    /**
     * Returns list of User's orders by id
     *
     * @param clientId user's id
     * @return list of objects Orders
     */
    public List<Order> getOrdersOfUser(Long clientId) {
        return transactionManager.createQuery("FROM Order AS r WHERE r.clientId.clientId = :clientId " +
                "ORDER BY r.dateOfCreation DESC", Order.class)
                .setParameter("clientId", clientId).getResultList();
    }

    /**
     * Returns number of orders
     *
     * @return long value of size
     */
    public Long size() {

        return (Long) transactionManager.createQuery("select count(OrderId) from Order").getSingleResult();
    }

    /**
     * Returns list of stricted orders to display on front-end
     *
     * @param startingValue   initial offset
     * @param quantityResults number of returned results
     * @return list of orders
     */
    public List<Order> getPagedOrders(int startingValue, int quantityResults) {

        return transactionManager.createQuery("FROM Order AS r ORDER BY r.dateOfCreation DESC", Order.class)
                .setFirstResult(startingValue).setMaxResults(quantityResults).getResultList();
    }
}
