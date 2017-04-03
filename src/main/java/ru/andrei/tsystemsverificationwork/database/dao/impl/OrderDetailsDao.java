package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;

import java.util.List;

@Component("orderDetailsDao")
public class OrderDetailsDao extends GenericDao<OrderDetail> {

    public OrderDetailsDao() {
        setClazz(OrderDetail.class);
    }

    public List<OrderDetail> getOrderDetailsById(Long orderId) {

        return transactionManager.createQuery("FROM OrderDetail AS r " +
                "WHERE r.orderDetailPk.order.orderId = :orderId", OrderDetail.class)
                .setParameter("orderId", orderId).getResultList();
    }


}
