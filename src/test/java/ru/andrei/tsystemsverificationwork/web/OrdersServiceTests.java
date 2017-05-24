package ru.andrei.tsystemsverificationwork.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.impl.OrdersService;

import java.util.HashMap;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrdersServiceTests {

    @Autowired
    private OrdersService ordersService;

    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderZero() {

        ordersService.getOrder(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderMinValue() {

        ordersService.getOrder(Long.MIN_VALUE);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetOrderMaxValue() {

        ordersService.getOrder(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNull() {

        ordersService.createOrder(null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderNullTwo() {

        ordersService.createOrder(new HashMap<>(), PaymentMethod.CARD,
                DeliveryMethod.NEXT_DAY_DELIVERY, new ClientAddress());
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testUpdateOrderNotAuth() {

        ordersService.updateOrderStatus(OrderStatus.AWAITING_SHIPMENT, null);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void testUpdateOrderAuth() {

        ordersService.updateOrderStatus(OrderStatus.AWAITING_SHIPMENT, null);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetAddressByOrderId() {

        ordersService.getAddressByOrderId(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAddressByOrderIdNull() {

        ordersService.getAddressByOrderId(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAddressByOrderIdZero() {

        ordersService.getAddressByOrderId(0L);
    }
}
