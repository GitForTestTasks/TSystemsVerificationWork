package ru.andrei.tsystemsverificationwork.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.services.impl.PaginationService;

import static junit.framework.TestCase.assertNotNull;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PaginationServiceTests {

    @Autowired
    private PaginationService paginationService;

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageOne() {

        paginationService.getGoodsPage(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageTwo() {

        paginationService.getGoodsPage(1, 0);
    }

    @Test
    public void testGetGoodsPageThree() {

        assertNotNull(paginationService.getGoodsPage(10, 1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageFour() {

        paginationService.getGoodsPage(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodsPageFive() {

        paginationService.getGoodsPage(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void testGetGoodsPageSix() {

        assertNotNull(paginationService.getGoodsPage(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetAdminPagedOrdersNotAuth() {

        paginationService.getAdminPagedOrders(null, null);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void testGetAdminPagedOrdersAuth() {

        paginationService.getAdminPagedOrders(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAdminPagedOrdersAuthAdmin() {

        paginationService.getAdminPagedOrders(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAdminPagedOrdersMinValue() {

        paginationService.getAdminPagedOrders(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAdminPagedOrdersMaxValue() {

        assertNotNull(paginationService.getAdminPagedOrders(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testUpdateOrderAuthAdmin() {

        paginationService.getAdminPagedOrders(null, null);
    }
}
