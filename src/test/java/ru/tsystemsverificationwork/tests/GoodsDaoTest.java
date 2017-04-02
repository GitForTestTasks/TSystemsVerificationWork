package ru.tsystemsverificationwork.tests;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystemsverificationwork.database.dao.impl.GoodsDao;

//@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:datasource.xml","classpath:security-context.xml"
        ,"classpath:service-context.xml"}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsDaoTest {


    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private DataSource dataSource;


    @Test
    public void testCreateRetrieve() {

        Logger log = Logger.getLogger(ClientsDaoTests.class.getName());
//
//        List<Good> goods = goodsDao.getStrictedGoodsList(0,10);
//
//        Long size = goodsDao.size();
//
//        log.log(Level.WARNING, goods.toString());
//        log.log(Level.WARNING, size.toString());


                log.log(Level.WARNING, goodsDao.searchByBrandAndColour("WHITE", "WHITE").toString());

        assertTrue(true);

    }
}