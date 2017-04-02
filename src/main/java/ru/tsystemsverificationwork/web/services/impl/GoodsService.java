package ru.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.tsystemsverificationwork.database.models.Good;

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

        if (categoriesDao.categoryExists(good.getCategory().getName())) {
            good.setCategory(categoriesDao.findByName(good.getCategory().getName()));
        }
        
        goodsDao.create(good);
    }

    public List<Good> getGoodsPage(int page, int quantityOfElements) {

        Long size = goodsDao.size();

        page = page - 1;
        if (page * quantityOfElements > size || page < 0)
            return new ArrayList<>();


        return goodsDao.getStrictedGoodsList(page * quantityOfElements, quantityOfElements);

    }

    public List<Good> search(String brand, String colour) {
        return goodsDao.searchByBrandAndColour(brand, colour);
    }

    public Long goodsSize() {
        return goodsDao.size();
    }
}