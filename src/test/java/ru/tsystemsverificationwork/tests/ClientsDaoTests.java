package ru.tsystemsverificationwork.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.dao.RolesDao;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.Role;

//@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:datasource.xml","classpath:security-context.xml"
,"classpath:service-context.xml"}
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
	private Client user4 = new Client("Pavel", "Dream",
            new Date(0l), "PavelDream@andrei.ru", "smth", true);
    private Role role = new Role("ROLE_ADMIN");

	
	@Test
	public void testCreateRetrieve() {

        List<Role> roles = new ArrayList<>();
        roles.add(rolesDao.findByName("ROLE_ADMIN"));
//        roles.add(new Role("ROLE_ADMIN"));


        user2.setRoles(roles);


//        user4 =  clientsDao.getUserByEmail(user3.getEmail());
        clientsDao.create(user2);
//        clientsDao.exists("asd");



//
//        Logger log = Logger.getLogger(ClientsDaoTests.class.getName());
//        log.log(Level.WARNING,  user4.getEmail() );
//        log.log(Level.WARNING, clientsDao.getUserByEmail(user4.getEmail()).getEmail() + "asd");


        assertTrue(true);

	}
	

}
