package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientAddressDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrderDetailsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;
import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.OutOfStockException;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Orders business logic
 */
@Transactional
@Service("ordersService")
public class OrdersService extends GenericService {

    /**
     * Dao of OrderDetails entity
     */
    private OrderDetailsDao orderDetailsDao;
    /**
     * Order's Dao
     */
    private OrdersDao ordersDao;
    /**
     * Dao of client's addresses
     */
    private ClientAddressDao clientAddressDao;
    /**
     * Good's Dao
     */
    private GoodsDao goodsDao;
    /**
     * Business logic of statistics
     */
    private StatisticsService statisticsService;
    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    public OrdersService(OrderDetailsDao orderDetailsDao, OrdersDao ordersDao, ClientAddressDao clientAddressDao,
                         GoodsDao goodsDao, StatisticsService statisticsService) {
        this.orderDetailsDao = orderDetailsDao;
        this.ordersDao = ordersDao;
        this.clientAddressDao = clientAddressDao;
        this.goodsDao = goodsDao;
        this.statisticsService = statisticsService;
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
            throw new ItemNotFoundException("Order not found");
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
     * Returns stricted list of orders on submitted page
     *
     * @param page               number of page
     * @param quantityOfElements number of elements per page
     * @return list of Orders objects
     */
    @Secured("ROLE_ADMIN")
    public List<Order> getAdminPagedOrders(Integer page, Integer quantityOfElements) {

        Integer localPage = page;

        if (localPage == null || quantityOfElements == null || localPage < 1 || quantityOfElements < 1)
            throw new IllegalArgumentException();

        Long size = ordersDao.size();

        localPage = localPage - 1;
        if (localPage * quantityOfElements > size || localPage < 0)
            return new ArrayList<>();


        return ordersDao.getPagedOrders(localPage * quantityOfElements, quantityOfElements);
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

        createOrderDetails(orderDetails);

        return true;
    }

    /**
     * Reserve goods by submitted map id to quantity
     *
     * @param goods map represents cart
     */
    private synchronized void reserveGoods(Map<Good, Integer> goods) {

        for (Map.Entry<Good, Integer> entry : goods.entrySet()) {

            Good good = entry.getKey();
            int count = good.getCount();
            int quantityBought = entry.getValue();

            if (count >= quantityBought)
                count = count - quantityBought;
            else
                throw new OutOfStockException("We cannot reserve more than " + good.getCount() + " but required was " +
                        +quantityBought + " for item " + good.getTitle());

            good.setCount(count);
            goodsDao.update(good);
            log.info(quantityBought + " of " + good.getTitle() + " have been reserved, " + good.getCount() + " left");
        }
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
            reserveGoods(getRelatedGoods(orderId));
        }

        order.setOrderStatus(orderStatus);
        ordersDao.update(order);
        statisticsService.forceUpdate();

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
            throw new ItemNotFoundException("Address does not exist");
        else return order.getClientAddressId();
    }
}
