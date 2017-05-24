package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.OutOfStockException;

import java.util.Map;

/**
 * Business logic of reserving products
 */
@Service
public class ReserveService {

    /**
     * Good's Dao
     */
    private GoodsDao goodsDao;
    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(ReserveService.class);

    @Autowired
    public ReserveService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    /**
     * Reserve goods by submitted map id to quantity
     *
     * @param goods map represents cart
     */
    public synchronized void reserveGoods(Map<Good, Integer> goods) {

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
            log.info("{} of {} have been reserved, {} left", quantityBought, good.getTitle(), good.getCount());
        }
    }
}
