package ru.andrei.tsystemsverificationwork.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.web.services.impl.ProfileService;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTests {

    @Autowired
    private ProfileService profileService;

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateInformation() {

        profileService.updateInformation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCalledClientAddressNull() {

        profileService.getCalledClientAddress(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCalledClientAddressZero() {

        profileService.getCalledClientAddress(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCliendAddress() {

        profileService.getCalledClientAddress(null);
    }


}
