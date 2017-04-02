package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.tsystemsverificationwork.database.models.Client;
import ru.tsystemsverificationwork.database.models.ClientAddress;
import ru.tsystemsverificationwork.database.models.Good;

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
