package ru.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.tsystemsverificationwork.database.dao.impl.OrderDetailsDao;
import ru.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.tsystemsverificationwork.database.models.ClientAddress;
import ru.tsystemsverificationwork.database.models.Good;
import ru.tsystemsverificationwork.database.models.Order;
import ru.tsystemsverificationwork.database.models.OrderDetail;
import ru.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.tsystemsverificationwork.web.services.GenericService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@Transactional
@Service("ordersService")
public class OrdersService extends GenericService {

    private OrderDetailsDao orderDetailsDao;
    private OrdersDao ordersDao;
    private GoodsDao goodsDao;
    private ClientAddressDao clientAddressDao;

    @Autowired
    public OrdersService(OrderDetailsDao orderDetailsDao, OrdersDao ordersDao,
                         GoodsDao goodsDao, ClientAddressDao clientAddressDao) {
        this.orderDetailsDao = orderDetailsDao;
        this.ordersDao = ordersDao;
        this.goodsDao = goodsDao;
        this.clientAddressDao = clientAddressDao;
    }

    public Long orderSize() {

        return ordersDao.size();
    }

    public List<Order> getMyOrders() {

        List<Order> orders = ordersDao.getOrdersOfUser(getCurrentUser().getClientId());
        if (orders == null)
            return new ArrayList<>();
        else return orders;
    }

    public List<Order> getAdminPagedOrders(Integer page, Integer quantityOfElements) {

        Long size = ordersDao.size();

        page = page - 1;
        if (page * quantityOfElements > size || page < 0)
            return new ArrayList<>();


        return ordersDao.getPagedOrders(page * quantityOfElements, quantityOfElements);
    }

    public List<Order> getAdminOrders() {
        return null;
    }


    public boolean createOrder(Map<Integer, Integer> cart, PaymentMethod paymentMethod,
                               DeliveryMethod deliveryMethod, ClientAddress clientAddress) {

        Order order = new Order();
        order.setPaymentMethod(paymentMethod);
        order.setDeliveryMethod(deliveryMethod);
        order.setDateOfCreation(new Timestamp(Calendar.getInstance().getTime().getTime()));


        order.setClientId(getCurrentUser());
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        order.setOrderStatus(OrderStatus.NOT_PAID);

        Long checkClientId = clientAddress.getClientAddressId();
        if (verificateRequestedAddress(checkClientId)) {
            clientAddress = clientAddressDao.findOne(checkClientId);
        } else return false;


        order.setClientAddressId(clientAddress);
        ordersDao.create(order);


        Map<Good, Integer> orderDetails = showCartItems(cart);


        createOrderDetails(orderDetails);

        return true;
    }

    @Transactional
    private void createOrderDetails(Map<Good, Integer> orderDetails) {

        Order order = ordersDao.findOrder(getCurrentUser().getClientId());

        for (Map.Entry<Good, Integer> iterated : orderDetails.entrySet()) {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setGood(iterated.getKey());
            orderDetail.setQuantity(iterated.getValue());
            orderDetailsDao.create(orderDetail);
        }
    }
}
