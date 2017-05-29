package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Orders business logic
 */
@Transactional
@Service("ordersService")
public class OrdersService extends GenericService {

    /**
     * Order's Dao
     */
    private OrdersDao ordersDao;
    /**
     * Dao of client's addresses
     */
    private ClientAddressDao clientAddressDao;
    /**
     * Business logic of statistics
     */
    private StatisticsService statisticsService;
    /**
     * Business logic of order details
     */
    private OrdersDetailsService ordersDetailsService;
    /**
     * Business logic of reserving products
     */
    private ReserveService reserveService;
    /**
     * Resolver class
     */
    private ResolverService resolverService;

    @Autowired
    public OrdersService(OrdersDao ordersDao, ClientAddressDao clientAddressDao,
                         StatisticsService statisticsService,
                         OrdersDetailsService ordersDetailsService,
                         ReserveService reserveService,
                         ResolverService resolverService) {
        this.ordersDao = ordersDao;
        this.clientAddressDao = clientAddressDao;
        this.statisticsService = statisticsService;
        this.ordersDetailsService = ordersDetailsService;
        this.reserveService = reserveService;
        this.resolverService = resolverService;
    }

    /**
     * Returns number of orders
     *
     * @return long value
     */
    public Long orderSize() {

        return ordersDao.size();
    }

    /**
     * Finds order by id
     *
     * @param id order id
     * @return Order object
     */
    public Order getOrder(Long id) {

        if (id == null || id < 1)
            throw new IllegalArgumentException();

        Order order = ordersDao.findOne(id);
        if (order == null)
            throw new ItemNotFoundException("Order not found.");
        else return order;
    }

    /**
     * Returns list of current user's orders
     *
     * @return list of orders objects
     */
    public List<Order> getMyOrders() {

        List<Order> orders = ordersDao.getOrdersOfUser(getCurrentUser().getClientId());
        if (orders == null)
            return new ArrayList<>();
        else return orders;
    }

    /**
     * Creates order
     *
     * @param cart           map with orders ids and quantity bought
     * @param paymentMethod  payment method
     * @param deliveryMethod delivery method
     * @param clientAddress  to which address client is going to buy products
     * @return boolean value if order created
     */
    public boolean createOrder(Map<Integer, Integer> cart, PaymentMethod paymentMethod,
                               DeliveryMethod deliveryMethod, ClientAddress clientAddress) {

        ClientAddress localClientAddress = clientAddress;

        boolean condition = cart == null || cart.isEmpty();
        boolean conditionTwo = deliveryMethod == null || clientAddress == null || paymentMethod == null;

        if (condition || conditionTwo)
            throw new IllegalArgumentException();

        Order order = new Order();
        order.setPaymentMethod(paymentMethod);
        order.setDeliveryMethod(deliveryMethod);
        order.setDateOfCreation(new Timestamp(Calendar.getInstance().getTime().getTime()));
        order.setClientId(getCurrentUser());
        order.setPaymentStatus(PaymentStatus.NOT_PAID);
        order.setOrderStatus(OrderStatus.NOT_PAID);

        Long checkClientId = localClientAddress.getClientAddressId();
        if (verificateRequestedAddress(checkClientId)) {
            localClientAddress = clientAddressDao.findOne(checkClientId);
        } else return false;

        order.setClientAddressId(localClientAddress);
        ordersDao.create(order);

        Map<Good, Integer> orderDetails = showCartItems(cart);

        ordersDetailsService.createOrderDetails(orderDetails);

        return true;
    }

    /**
     * Updates order status due admin's interface.
     * Should be protected by security.
     *
     * @param orderStatus order's status
     * @param orderId     order's id
     * @return boolean value if order updated
     */
    @Secured("ROLE_ADMIN")
    public boolean updateOrderStatus(OrderStatus orderStatus, Long orderId) {

        if (orderStatus == null || orderId == null || orderId < 1)
            throw new IllegalArgumentException();

        Order order = ordersDao.findOne(orderId);

        if (orderStatus != OrderStatus.NOT_PAID && order.getOrderStatus() == OrderStatus.NOT_PAID) {
            reserveService.reserveGoods(resolverService.getRelatedGoods(orderId));
            order.setDateOfSale(new Timestamp(Calendar.getInstance().getTime().getTime()));
        }

        order.setOrderStatus(orderStatus);
        ordersDao.update(order);
        statisticsService.forceUpdate();

        return true;
    }

    /**
     * Returns address of order
     *
     * @param orderId id of order
     * @return ClientAddress entity
     */
    public ClientAddress getAddressByOrderId(Long orderId) {

        if (orderId == null || orderId < 1)
            throw new IllegalArgumentException();

        Order order = ordersDao.findOne(orderId);
        if (order == null)
            throw new ItemNotFoundException("Address does not exist.");
        else return order.getClientAddressId();
    }
}
