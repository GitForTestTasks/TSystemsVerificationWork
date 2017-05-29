package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrderDetailsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import java.util.Map;

/**
 * Service responsible for order details
 */
@Service
public class OrdersDetailsService extends GenericService {

    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(OrdersDetailsService.class);
    /**
     * Dao of orders
     */
    private OrdersDao ordersDao;
    /**
     * Dao of order details
     */
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    public OrdersDetailsService(OrdersDao ordersDao, OrderDetailsDao orderDetailsDao) {
        this.ordersDao = ordersDao;
        this.orderDetailsDao = orderDetailsDao;
    }

    /**
     * Creating details of bought goods to order
     *
     * @param orderDetails Order details is going to be persisted
     */
    public void createOrderDetails(Map<Good, Integer> orderDetails) {

        Order order = ordersDao.findOrder(getCurrentUser().getClientId());

        for (Map.Entry<Good, Integer> iterated : orderDetails.entrySet()) {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setGood(iterated.getKey());
            orderDetail.setQuantity(iterated.getValue());
            log.info("Customer ordered " + iterated.getKey().getTitle() + " to order " + order.getOrderId() + " id.");
            orderDetailsDao.create(orderDetail);
        }

        log.info("Order with id {} has been created.", order.getClientId());
    }
}
