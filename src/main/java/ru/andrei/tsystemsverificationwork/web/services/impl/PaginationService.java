package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.OrdersDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for pagination
 */
@Service
public class PaginationService {

    private OrdersDao ordersDao;
    private GoodsDao goodsDao;

    @Autowired
    public PaginationService(OrdersDao ordersDao, GoodsDao goodsDao) {
        this.ordersDao = ordersDao;
        this.goodsDao = goodsDao;
    }

    /**
     * Returns stricted list of orders on submitted page
     *
     * @param page               number of page
     * @param quantityOfElements number of elements per page
     * @return list of Orders objects
     */
    @Secured("ROLE_ADMIN")
    public List<Order> getAdminPagedOrders(Integer page, Integer quantityOfElements) {

        Integer localPage = page;

        if (localPage == null || quantityOfElements == null || localPage < 1 || quantityOfElements < 1)
            throw new IllegalArgumentException();

        Long size = ordersDao.size();

        localPage = localPage - 1;
        if (localPage * quantityOfElements > size || localPage < 0)
            return new ArrayList<>();

        return ordersDao.getPagedOrders(localPage * quantityOfElements, quantityOfElements);
    }

    /**
     * Returns list of goods should be showed on current page
     *
     * @param page               number of page
     * @param quantityOfElements lements per page
     * @return list of goods objects
     */
    public List<Good> getGoodsPage(int page, int quantityOfElements) {

        int localPage = page;

        if (localPage <= 0 || quantityOfElements <= 0)
            throw new IllegalArgumentException();

        Long size = goodsDao.size();

        localPage = localPage - 1;
        if (localPage * quantityOfElements > size || localPage < 0)
            return new ArrayList<>();

        return goodsDao.getStrictedGoodsList(localPage * quantityOfElements, quantityOfElements);
    }
}
