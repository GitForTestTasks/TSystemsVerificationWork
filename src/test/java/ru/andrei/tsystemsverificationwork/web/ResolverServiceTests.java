package ru.andrei.tsystemsverificationwork.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;
import ru.andrei.tsystemsverificationwork.web.services.impl.ResolverService;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ResolverServiceTests {

    @Autowired
    private ResolverService resolverService;

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelatedGoodsNull() {

        resolverService.getRelatedGoods(null);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetRelatedGoods() {

        resolverService.getRelatedGoods(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelatedGoodsMax() {

        resolverService.getRelatedGoods(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelatedGoodsZero() {

        resolverService.getRelatedGoods(0L);
    }
}
