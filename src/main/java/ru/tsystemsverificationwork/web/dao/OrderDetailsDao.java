package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.OrderDetail;

@Transactional
@Component("orderDetailsDao")
public class OrderDetailsDao extends GenericDao<OrderDetail> {

    public OrderDetailsDao() {
        setClazz(OrderDetail.class);
    }


}
