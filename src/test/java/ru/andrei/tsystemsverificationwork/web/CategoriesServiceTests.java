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
import ru.andrei.tsystemsverificationwork.web.services.impl.CategoriesService;

import static junit.framework.TestCase.assertNotNull;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriesServiceTests {

    @Autowired
    private CategoriesService categoriesService;

    public CategoriesServiceTests() {
    }

    @Test
    public void testNullAllCategories() {

        assertNotNull(categoriesService.getAllCategories());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCategoryById() {

        categoriesService.getCategoryById(null);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testMaxCategoryById() {

        categoriesService.getCategoryById(Long.MAX_VALUE);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testCreateCategoryNull() {

        categoriesService.createCategory(null);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void testCreateCategoryNullLogged() {

        categoriesService.createCategory(null);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testCreateCategoryNullLoggedAdmin() {

        categoriesService.createCategory(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCategoryExistsNull() {

        categoriesService.categoryExists(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCategoryExistsEmptyString() {

        categoriesService.categoryExists("");
    }
}
