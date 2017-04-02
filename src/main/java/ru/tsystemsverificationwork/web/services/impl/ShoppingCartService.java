package ru.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.tsystemsverificationwork.database.models.ClientAddress;
import ru.tsystemsverificationwork.database.models.Good;
import ru.tsystemsverificationwork.web.services.GenericService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service("shoppingCartService")
@Transactional
public class ShoppingCartService extends GenericService {

    private GoodsDao goodsDao;

    @Autowired
    public ShoppingCartService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public Set<ClientAddress> retriveAllAddresses() {

        Set<ClientAddress> clientAddresses = getCurrentUser().getClientAddresses();

        if (clientAddresses == null) {
            return new HashSet<>();
        } else return clientAddresses;
    }

}
