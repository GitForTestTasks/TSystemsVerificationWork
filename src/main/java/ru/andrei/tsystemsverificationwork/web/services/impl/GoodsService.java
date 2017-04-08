package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service("goodsService")
@Transactional
public class GoodsService {

    private GoodsDao goodsDao;
    private CategoriesDao categoriesDao;

    @Autowired
    public GoodsService(GoodsDao goodsDao, CategoriesDao categoriesDao) {
        this.goodsDao = goodsDao;
        this.categoriesDao = categoriesDao;
    }


    @Secured("ROLE_ADMIN")
    public void createGood(Good good) {

        if (good == null)
            throw new IllegalArgumentException();

        if (categoriesDao.categoryExists(good.getCategory().getName())) {
            good.setCategory(categoriesDao.findByName(good.getCategory().getName()));
        }

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
                             Long maxPrice) {



        return goodsDao.searchByBrandAndColour(brand, colour, title,
                new BigDecimal(minPrice), new BigDecimal(maxPrice));
    }

    public Long goodsSize() {
        return goodsDao.size();
    }
}
