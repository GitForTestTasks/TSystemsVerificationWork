package ru.andrei.tsystemsverificationwork.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.impl.ShoppingCartService;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartServiceTests {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyQuantityNull() {

        shoppingCartService.verifyQuantity(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyQuantityNullTwo() {

        shoppingCartService.verifyQuantity(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyQuantity() {

        shoppingCartService.verifyQuantity(100, 0);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testVerifyQuantityMaxValue() {

        shoppingCartService.verifyQuantity(Integer.MAX_VALUE, 1);
    }
}
