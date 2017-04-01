package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Order;


//@Transactional
@Component("ordersDao")
public class OrdersDao extends GenericDao<Order> {

    public OrdersDao() {
        setClazz(Order.class);
    }
}
