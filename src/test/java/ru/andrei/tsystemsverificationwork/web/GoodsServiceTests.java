package ru.andrei.tsystemsverificationwork.web;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.services.impl.GoodsService;

import java.math.BigDecimal;


@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
public class GoodsServiceTests {

    @Autowired
    private GoodsService goodsService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testCreateGoodNull() {

        goodsService.createGood(null);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void testCreateGoodNullLogged() {

        goodsService.createGood(null);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testCreateGoodNullLoggedAdmin() {

        goodsService.createGood(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodByIdNull() {

        goodsService.getGoodById(0L);
    }

    @Test
    public void testGetGoodByIdOne() {

        Good good = goodsService.getGoodById(1L);
        Assert.assertEquals(good.getGoodId(), 1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testDeleteGoodNotValid() {

        Good good = new Good();
        BigDecimal bigDecimal = new BigDecimal(123);
        Category category = new Category();
        category.setName("Test");
        good.setCount(20);
        good.setTitle("Test");
        good.setCategory(category);
        good.setPrice(bigDecimal);

        goodsService.createGood(good);
    }

    @Test
    public void testGoodsSize() {

        Assert.assertNotNull(goodsService.goodsSize());
    }

    @Test
    public void testSearch() {

        Assert.assertNotNull(goodsService.search("", "", "", 0L, 0L, ""));
    }

    @Test
    public void testSearchTwo() {

        Assert.assertNotNull(goodsService.search("", "", "", 0L, 0L, "Boots"));
    }
}
