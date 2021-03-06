package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.OutOfStockException;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

/**
 * Business logic of shopping cart
 */
@Service("shoppingCartService")
@Transactional
public class ShoppingCartService extends GenericService {

    /**
     * Good's Dao
     */
    private GoodsDao goodsDao;

    @Autowired
    public ShoppingCartService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    /**
     * Checks if whether goods are out of stock or not.
     *
     * @param goodId   good's id
     * @param quantity quantity bought
     * @return boolean value of result
     */
    public boolean verifyQuantity(Integer goodId, Integer quantity) {

        if (goodId == null || quantity == null || goodId < 1 || quantity < 1)
            throw new IllegalArgumentException();

        Good check = goodsDao.findOne(goodId);
        if (check == null) {
            throw new ItemNotFoundException("We can not find product with " + goodId + " id.");
        }

        if (check.getCount() < quantity)
            throw new OutOfStockException("We do not have quantity " + quantity + " of product " + goodId + " id.");
        else return true;
    }
}
