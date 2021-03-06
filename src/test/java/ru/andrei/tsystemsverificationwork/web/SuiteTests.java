package ru.andrei.tsystemsverificationwork.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CategoriesServiceTests.class,
        ClientsServiceTests.class,
        GoodsServiceTests.class,
        OrdersServiceTests.class,
        ProfileServiceTests.class,
        ShoppingCartServiceTests.class,
        PaginationServiceTests.class,
        ResolverServiceTests.class,
        LoginServiceTests.class,
        ReserveGoodsTests.class})
public class SuiteTests {
}
