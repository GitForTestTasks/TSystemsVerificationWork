package ru.andrei.tsystemsverificationwork.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrei.tsystemsverificationwork.database.dao.impl.RolesDao;
import ru.andrei.tsystemsverificationwork.database.models.Role;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.models.Client;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"

        }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientsDaoTests {


    @Autowired
    private ClientsDao clientsDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private DataSource dataSource;

    private Client user1 = new Client("Ivan", "Petrov",
            new Date(0l), "IvanPetrov@andrei.ru", "smth");
    private Client user2 = new Client("Ivan", "Sidorov",
            new Date(0l), "IvanSidorov@andrei.ru", "smth");
    private Client user3 = new Client("Petya", "Ivanov",
            new Date(0l), "PetyaIvanov@andrei.ru", "smth");
    private Client user4 = new Client("Pavel", "Antonov",
            new Date(0l), "Pavel@andrei.ru", "smth", true);
    private Role role = new Role("ROLE_ADMIN");


    @Test
    public void testCreateRetrieve() {

        List<Role> roles = new ArrayList<>();
        roles.add(rolesDao.findByName("ROLE_ADMIN"));
//        roles.add(new Role("ROLE_ADMIN"));


        user2.setRoles(roles);


        clientsDao.create(user2);


        assertTrue(true);

    }


}
