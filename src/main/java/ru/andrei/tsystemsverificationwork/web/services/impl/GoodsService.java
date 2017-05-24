package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;

import java.math.BigDecimal;
import java.util.List;

/**
 * Business logic of goods
 */
@Service("goodsService")
@Transactional
public class GoodsService {

    /**
     * Jms service
     */
    private StatisticsService statisticsService;
    /**
     * Dao of goods
     */
    private GoodsDao goodsDao;
    /**
     * Dao of categories
     */
    private CategoriesDao categoriesDao;
    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    public GoodsService(StatisticsService statisticsService, GoodsDao goodsDao, CategoriesDao categoriesDao) {
        this.statisticsService = statisticsService;
        this.goodsDao = goodsDao;
        this.categoriesDao = categoriesDao;
    }

    /**
     * Finds good entity by id
     *
     * @param goodId id to be found
     * @return good found
     */
    public Good getGoodById(Long goodId) {

        if (goodId == null || goodId < 1)
            throw new IllegalArgumentException();

        return goodsDao.findOne(goodId);
    }

    /**
     * Deletes row in goods table by id
     *
     * @param goodId id to be deleted
     */
    @Secured("ROLE_ADMIN")
    public void deleteGood(Long goodId) {

        if (goodId == null || goodId < 1)
            throw new IllegalArgumentException();

        Good good = goodsDao.findOne(goodId);
        goodsDao.delete(good);
        statisticsService.checkIfUpdateNeeded(goodId);
        log.info("Good id " + good.getGoodId() + " " + good.getTitle() + " has been marked as deleted");
    }

    /**
     * Adds row in goods table
     *
     * @param good entity to be added
     */
    @Secured("ROLE_ADMIN")
    public void createGood(Good good) {

        if (good == null)
            throw new IllegalArgumentException();

        if (categoriesDao.categoryExists(good.getCategory().getName())) {
            good.setCategory(categoriesDao.findByName(good.getCategory().getName()));
        }

        log.info("Changes for good " + good.getTitle() + " submitted");

        if (good.getGoodId() != 0)
            statisticsService.checkIfUpdateNeeded(good.getGoodId());

        goodsDao.create(good);
    }

    /**
     * Returns list of found goods by submitted parameters
     *
     * @param brand    brand of good
     * @param colour   colour of good
     * @param title    title of good
     * @param minPrice minimal price
     * @param maxPrice maximal price
     * @param category category of good
     * @return list of goods objects
     */
    public List<Good> search(String brand, String colour, String title, Long minPrice,
                             Long maxPrice, String category) {

        if (category == null || category.isEmpty())
            return goodsDao.searchByFormDefaultCategory(brand, colour, title,
                    new BigDecimal(minPrice), new BigDecimal(maxPrice));

        return goodsDao.searchByForm(brand, colour, title,
                new BigDecimal(minPrice), new BigDecimal(maxPrice), category);
    }

    /**
     * Returns number of products in market
     *
     * @return long value
     */
    public Long goodsSize() {
        return goodsDao.size();
    }
}
