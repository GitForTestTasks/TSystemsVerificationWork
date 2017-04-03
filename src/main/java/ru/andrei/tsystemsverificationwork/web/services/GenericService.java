package ru.andrei.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Good;

import java.util.HashMap;
import java.util.Map;

@Service
public abstract class GenericService {

    @Autowired
    private ClientsDao clientsDao;

    @Autowired
    private GoodsDao goodsDao;


    public Client getCurrentUser() {

        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clientsDao.getUserByEmail(userDetail.getUsername());
    }

    public boolean verificateRequestedOrder(Long orderId) {

        if(orderId == null || getCurrentUser().getOrders() == null) {
            return false;
        }
        

        return getCurrentUser().getOrders().contains()
    }

    public boolean verificateRequestedAddress(Long clientAddressId) {

        if (clientAddressId == null || getCurrentUser().getClientAddresses() == null) {
            return false;
        }
        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(clientAddressId);

        return getCurrentUser().getClientAddresses().contains(clientAddress);
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
