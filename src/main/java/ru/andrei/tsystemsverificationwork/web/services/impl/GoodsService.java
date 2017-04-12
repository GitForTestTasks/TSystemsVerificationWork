package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.database.models.Good;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service("goodsService")
@Transactional
public class GoodsService {

    private GoodsDao goodsDao;
    private CategoriesDao categoriesDao;
    private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    public GoodsService(GoodsDao goodsDao, CategoriesDao categoriesDao) {
        this.goodsDao = goodsDao;
        this.categoriesDao = categoriesDao;
    }

    public Good getGoodById(Long goodId) {

        if (goodId == null || goodId < 1)
            throw new IllegalArgumentException();

        return goodsDao.findOne(goodId);
    }

    @Secured("ROLE_ADMIN")
    public void createGood(Good good) {

        if (good == null)
            throw new IllegalArgumentException();

        if (categoriesDao.categoryExists(good.getCategory().getName())) {
            good.setCategory(categoriesDao.findByName(good.getCategory().getName()));
        }

        log.info("Changes for good " + good.getTitle() + " submitted");
        goodsDao.create(good);
    }

    public List<Good> getGoodsPage(int page, int quantityOfElements) {

        if (page <= 0 || quantityOfElements <= 0)
            throw new IllegalArgumentException();

        Long size = goodsDao.size();

        page = page - 1;
        if (page * quantityOfElements > size || page < 0)
            return new ArrayList<>();


        return goodsDao.getStrictedGoodsList(page * quantityOfElements, quantityOfElements);

    }

    public List<Good> search(String brand, String colour, String title, Long minPrice,
                             Long maxPrice, String category) {

        if (category == null || category.isEmpty())
            return  goodsDao.searchByFormDefaultCategory(brand, colour, title,
                    new BigDecimal(minPrice), new BigDecimal(maxPrice));

        return goodsDao.searchByForm(brand, colour, title,
                new BigDecimal(minPrice), new BigDecimal(maxPrice), category);
    }

    public Long goodsSize() {
        return goodsDao.size();
    }
}
