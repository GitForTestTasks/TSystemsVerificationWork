package ru.tsystemsverificationwork.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.dao.GoodsDao;
import ru.tsystemsverificationwork.web.dao.RolesDao;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.Good;
import ru.tsystemsverificationwork.web.models.Role;

//@ActiveProfiles("dev")
@ContextConfiguration(locations = "classpath:datasource.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsDaoTest {


    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private DataSource dataSource;


    @Test
    public void testCreateRetrieve() {

        Logger log = Logger.getLogger(ClientsDaoTests.class.getName());

        List<Good> goods = goodsDao.getStrictedGoodsList(0,10);

        Long size = goodsDao.size();

        log.log(Level.WARNING, goods.toString());
        log.log(Level.WARNING, size.toString());


        assertTrue(true);

    }
}