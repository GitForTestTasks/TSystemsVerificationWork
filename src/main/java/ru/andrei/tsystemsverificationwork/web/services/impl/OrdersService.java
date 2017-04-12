package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
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
    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);


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

        if (orderId == null || orderId < 1)
            throw new IllegalArgumentException();

        List<OrderDetail> orderDetails = orderDetailsDao.getOrderDetailsById(orderId);

        if (orderDetails == null || orderDetails.size() == 0)
            throw new ItemNotFoundException("Order " + orderId + " does not have related items");


        Map<Good, Integer> resultMap = new HashMap<>();

        for (OrderDetail orderDetail : orderDetails) {
            Good good = orderDetail.getGood();
            resultMap.put(good, orderDetail.getQuantity());
        }

        return resultMap;
    }

    public Order getOrder(Long id) {

        if (id == null || id < 1)
            throw new IllegalArgumentException();

        Order order = ordersDao.findOne(id);
        if (order == null)
            throw new ItemNotFoundException("Order not found");
        else return order;
    }

    public List<Order> getMyOrders() {

        List<Order> orders = ordersDao.getOrdersOfUser(getCurrentUser().getClientId());
        if (orders == null)
            return new ArrayList<>();
        else return orders;
    }


    @Secured("ROLE_ADMIN")
    public List<Order> getAdminPagedOrders(Integer page, Integer quantityOfElements) {

        if (page == null || quantityOfElements == null || page < 1 || quantityOfElements < 1)
            throw new IllegalArgumentException();

        Long size = ordersDao.size();

        page = page - 1;
        if (page * quantityOfElements > size || page < 0)
            return new ArrayList<>();


        return ordersDao.getPagedOrders(page * quantityOfElements, quantityOfElements);
    }


    public boolean createOrder(Map<Integer, Integer> cart, PaymentMethod paymentMethod,
                               DeliveryMethod deliveryMethod, ClientAddress clientAddress) {

        if (cart == null || paymentMethod == null || deliveryMethod == null || clientAddress == null
                || cart.isEmpty())
            throw new IllegalArgumentException();

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

    @Secured("ROLE_ADMIN")
    public boolean updateOrderStatus(OrderStatus orderStatus, Long orderId) {

        if (orderStatus == null || orderId == null || orderId < 1)
            throw new IllegalArgumentException();


        Order order = ordersDao.findOne(orderId);

        if (orderStatus != OrderStatus.NOT_PAID)
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
            log.info("Customer ordered " + iterated.getKey().getTitle() + " to order " + order.getOrderId());
            orderDetailsDao.create(orderDetail);
        }

        log.info("Order with id " + order.getOrderId() + " has been created");
    }

    public ClientAddress getAddressByOrderId(Long orderId) {

        if (orderId == null || orderId < 1)
            throw new IllegalArgumentException();

        Order order = ordersDao.findOne(orderId);
        if (order == null)
            throw new ItemNotFoundException("Address does not exist");
        else return order.getClientAddressId();
    }
}
