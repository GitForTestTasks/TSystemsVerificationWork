package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.dao.GoodsDao;
import ru.tsystemsverificationwork.web.models.Good;

import java.util.HashMap;
import java.util.Map;


@Service("shoppingCartService")
@Transactional
public class ShoppingCartService {

    private GoodsDao goodsDao;

    @Autowired
    public ShoppingCartService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }


    public Map<Good, Integer> showCartItems(Map<Integer,Integer> cart) {

        Map<Good, Integer> goods = new HashMap<>();

        for (Map.Entry<Integer, Integer> goodId : cart.entrySet()) {

            goods.put(goodsDao.findOne(goodId.getKey()), goodId.getValue());
        }

        return goods;
    }
}
