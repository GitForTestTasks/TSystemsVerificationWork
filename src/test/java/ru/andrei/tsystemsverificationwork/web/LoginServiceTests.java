package ru.andrei.tsystemsverificationwork.web;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.services.impl.LoginService;

import javax.persistence.NoResultException;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceTests {

    @Autowired
    private LoginService loginService;

    @Test(expected = NoResultException.class)
    public void testLoadUserNotValid() {

        loginService.loadUserByUsername("notValidEmail");
    }

    @Test
    public void testLoadUserValid() {

        UserDetails userDetails = loginService.loadUserByUsername("IvanPetrov@andrei.ru");
        Assert.assertEquals(userDetails.getUsername(), "IvanPetrov@andrei.ru");
    }
}
