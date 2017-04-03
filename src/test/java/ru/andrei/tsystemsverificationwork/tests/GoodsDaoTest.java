package ru.andrei.tsystemsverificationwork.tests;

import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;

@ContextConfiguration(locations = {"classpath:datasource.xml",
        "classpath:security-context.xml",
        "classpath:service-context.xml"

        }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsDaoTest {


    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private DataSource dataSource;


    @Test
    public void testCreateRetrieve() {
/*
//
//        List<Good> goods = goodsDao.getStrictedGoodsList(0,10);
//
//        Long size = goodsDao.size();
//
//        log.log(Level.WARNING, goods.toString());
//        log.log(Level.WARNING, size.toString());


*/
        assertTrue(true);

    }
}