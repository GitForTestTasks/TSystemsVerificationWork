package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;

import java.util.List;

/**
 * Dao object of OrderDetail entity
 */
@Component("orderDetailsDao")
public class OrderDetailsDao extends GenericDao<OrderDetail> {

    public OrderDetailsDao() {
        setClazz(OrderDetail.class);
    }

    /**
     * Gets order details by submitted order id
     * Order details basically map order id to good id with quantity bought
     * @param orderId order id to find details
     * @return list of orderdetails objects
     */
    public List<OrderDetail> getOrderDetailsById(Long orderId) {

        return transactionManager.createQuery("FROM OrderDetail AS r " +
                "WHERE r.orderDetailPk.order.orderId = :orderId", OrderDetail.class)
                .setParameter("orderId", orderId).getResultList();
    }
}
