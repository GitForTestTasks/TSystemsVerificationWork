package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrderDetailsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;

import java.sql.Timestamp;
import java.util.*;


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

    public Map<Good, Integer> getRelatedGoods(Long orderId) {

        if (orderId == null)
            return new HashMap<>();

        List<OrderDetail> orderDetails = orderDetailsDao.getOrderDetailsById(orderId);
        Map<Good, Integer> resultMap = new HashMap<>();

        for (OrderDetail orderDetail : orderDetails) {
            Good good = orderDetail.getGood();
            resultMap.put(good, orderDetail.getQuantity());
        }

        return resultMap;
    }

    public Order getOrder(Long id) {

        Order order = ordersDao.findOne(id);
        if (order == null)
            return new Order();
        else return order;
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

    public boolean updateOrderStatus(OrderStatus orderStatus, Long orderId) {

        if(orderStatus == null || orderId == null)
            return false;


        Order order = ordersDao.findOne(orderId);

        if(orderStatus != OrderStatus.NOT_PAID)
            order.setDateOfSale(new Timestamp(Calendar.getInstance().getTime().getTime()));

        order.setOrderStatus(orderStatus);
        ordersDao.update(order);

        return true;
    }

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

    public ClientAddress getAddressByOrderId(Long orderId) {

        Order order = ordersDao.findOne(orderId);
        if (order == null)
            return new ClientAddress();
        else return order.getClientAddressId();

    }
}
