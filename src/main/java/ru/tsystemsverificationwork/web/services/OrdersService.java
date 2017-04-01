package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.dao.GoodsDao;
import ru.tsystemsverificationwork.web.dao.OrderDetailsDao;
import ru.tsystemsverificationwork.web.dao.OrdersDao;
import ru.tsystemsverificationwork.web.models.Order;


@Transactional
public class OrdersService {

    private OrderDetailsDao orderDetailsDao;
    private OrdersDao ordersDao;
    private GoodsDao goodsDao;

    @Autowired
    public OrdersService(OrderDetailsDao orderDetailsDao, OrdersDao ordersDao, GoodsDao goodsDao) {
        this.orderDetailsDao = orderDetailsDao;
        this.ordersDao = ordersDao;
        this.goodsDao = goodsDao;
    }

    public String createOrder() {



        return null;
    }
}
