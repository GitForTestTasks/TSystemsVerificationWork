package ru.andrei.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.VerificationException;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public abstract class GenericService {

    @Autowired
    private ClientsDao clientsDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private OrdersDao ordersDao;


    public Client getCurrentUser() {

        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientsDao.getUserByEmail(userDetail.getUsername());
    }

    public boolean verificateRequestedOrder(Long orderId) {

        if(orderId == null || getCurrentUser().getOrders() == null) {
            return false;
        }

        boolean result = getCurrentUser().getOrders().contains(ordersDao.findOne(orderId));

        if (result)
            return result;
        else
            throw new VerificationException("Request order", "You are not available to access requested information");
    }

    public boolean verificateRequestedAddress(Long clientAddressId) {

        if (clientAddressId == null || getCurrentUser().getClientAddresses() == null) {
            return false;
        }

        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(clientAddressId);

        boolean result = getCurrentUser().getClientAddresses().contains(clientAddress);
        if (result)
            return true;
        else
            throw new VerificationException("Request address", "You are not available to access requested information");
    }

    public Map<Good, Integer> showCartItems(Map<Integer, Integer> cart) {

        if (cart == null) {
            return new HashMap<>();
        }

        Map<Good, Integer> goods = new HashMap<>();

        for (Map.Entry<Integer, Integer> goodId : cart.entrySet()) {

            goods.put(goodsDao.findOne(goodId.getKey()), goodId.getValue());
        }

        return goods;
    }

}
