package ru.andrei.tsystemsverificationwork.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.OutOfStockException;
import ru.andrei.tsystemsverificationwork.web.services.impl.ReserveService;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
public class ReserveGoodsTests {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private GoodsDao goodsDao;

    private Map<Good, Integer> goodIntegerMap = new HashMap<>();

    private Good good;

    @Before
    public void init() {
        good = goodsDao.findOne(1);
    }

    @Test(expected = OutOfStockException.class)
    public void testReserveGoodsMaxValue() {

        goodIntegerMap.put(good, Integer.MAX_VALUE);
        reserveService.reserveGoods(goodIntegerMap);
    }

    @Test
    public void testReserveGoodsLegit() {

        goodIntegerMap.put(good, 0);
        reserveService.reserveGoods(goodIntegerMap);
    }
}
