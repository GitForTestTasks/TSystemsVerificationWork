package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrderDetailsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for resolving id's
 */
@Service
public class ResolverService {

    private OrderDetailsDao orderDetailsDao;

    @Autowired
    public ResolverService(OrderDetailsDao orderDetailsDao) {
        this.orderDetailsDao = orderDetailsDao;
    }

    /**
     * Returns goods related to order and quantity bought
     *
     * @param orderId id of order
     * @return map of goods to quantity
     */
    public Map<Good, Integer> getRelatedGoods(Long orderId) {

        if (orderId == null || orderId < 1)
            throw new IllegalArgumentException();

        List<OrderDetail> orderDetails = orderDetailsDao.getOrderDetailsById(orderId);

        if (orderDetails == null || orderDetails.isEmpty())
            throw new ItemNotFoundException("Order " + orderId + " does not have related items");

        Map<Good, Integer> resultMap = new HashMap<>();

        for (OrderDetail orderDetail : orderDetails) {
            Good good = orderDetail.getGood();
            resultMap.put(good, orderDetail.getQuantity());
        }

        return resultMap;
    }
}
