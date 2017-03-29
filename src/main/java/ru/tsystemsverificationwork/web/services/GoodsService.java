package ru.tsystemsverificationwork.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsystemsverificationwork.web.controllers.GoodsController;
import ru.tsystemsverificationwork.web.dao.GoodsDao;
import ru.tsystemsverificationwork.web.models.Good;

import java.util.ArrayList;
import java.util.List;


@Service("goodsService")
public class GoodsService {


    private GoodsDao goodsDao;

    @Autowired
    public GoodsService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Secured("ROLE_ADMIN")
    public void createGood(Good good) {

        goodsDao.create(good);
    }

    public List<Good> getGoodsPage(int page, int quantityOfElements) {

        Long size = goodsDao.size();

        page = page - 1;
        if(page*quantityOfElements > size || page < 0)
            return new ArrayList<>();


        return goodsDao.getStrictedGoodsList(page*quantityOfElements, quantityOfElements);

    }

    public List<Good> search(String brand, String colour) {
        return goodsDao.searchByBrandAndColour(brand, colour);
    }

    public Long goodsSize() {
        return  goodsDao.size();
    }




}
