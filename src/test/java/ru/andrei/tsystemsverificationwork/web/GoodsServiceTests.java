package ru.andrei.tsystemsverificationwork.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.impl.GoodsService;

import static junit.framework.TestCase.assertNotNull;


@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsServiceTests {

    @Autowired
    private GoodsService goodsService;

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageOne() {

        goodsService.getGoodsPage(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageTwo() {

        goodsService.getGoodsPage(1,0);
    }

    @Test
    public void testGetGoodsPageThree() {

        assertNotNull(goodsService.getGoodsPage(10, 1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageFour() {

        goodsService.getGoodsPage(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageFive() {

        goodsService.getGoodsPage(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void testGetGoodsPageSix() {

        assertNotNull(goodsService.getGoodsPage(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

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

}
