package ru.andrei.tsystemsverificationwork.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.services.impl.ClientsService;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientsServiceTests {

    @Autowired
    private ClientsService clientsService;

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAccountNull() {

        clientsService.createAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientExistsNull() {

        clientsService.clientExists(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientExistssEmptyString() {

        clientsService.clientExists("");
    }
}
