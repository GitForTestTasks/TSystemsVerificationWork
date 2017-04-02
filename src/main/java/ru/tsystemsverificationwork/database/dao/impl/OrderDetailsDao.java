package ru.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystemsverificationwork.database.dao.GenericDao;
import ru.tsystemsverificationwork.database.models.OrderDetail;

import javax.transaction.Transactional;

@Component("orderDetailsDao")
public class OrderDetailsDao extends GenericDao<OrderDetail> {

    public OrderDetailsDao() {
        setClazz(OrderDetail.class);
    }

}
