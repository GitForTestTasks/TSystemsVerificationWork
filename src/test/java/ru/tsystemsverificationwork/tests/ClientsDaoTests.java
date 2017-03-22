package ru.tsystemsverificationwork.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystemsverificationwork.web.dao.ClientsDao;
import ru.tsystemsverificationwork.web.models.Client;

//@ActiveProfiles("dev")
@ContextConfiguration(locations = {
//		context configs
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientsDaoTests {

/*	@Autowired
    private ClientsDao clientsDao;

	@Autowired
	private DataSource dataSource;
	
	private Client user1 = new Client("Ivan", "Petrov",
			Instant.now(), "IvanPetrov@andrei.ru", "smth");
	private Client user2 = new Client("Ivan", "Sidorov",
			Instant.now(), "IvanSidorov@andrei.ru", "smth");
	private Client user3 = new Client("Petya", "Ivanov",
			Instant.now(), "PetyaIvanov@andrei.ru", "smth");
	private Client user4 = new Client("Pavel", "Dream",
			Instant.now(), "PavelDream@andrei.ru", "smth");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		clientsDao.create(user1);
		
		List<Client> users1 = clientsDao.getAllClients();
		
		assertEquals("One user should have been created and retrieved", 1, users1.size());
		
		assertEquals("Inserted user should match retrieved", user1, users1.get(0));
		
		clientsDao.create(user2);
		clientsDao.create(user3);
		clientsDao.create(user4);
		
		List<Client> users2 = clientsDao.getAllClients();
		
		assertEquals("Should be four retrieved users.", 4, users2.size());
	}
	
	@Test
	public void testExists() {
		clientsDao.create(user1);
		clientsDao.create(user2);
		clientsDao.create(user3);
		
		assertTrue("User should exist.", clientsDao.exists(user2.geteMail()));
		assertFalse("User should not exist.", clientsDao.exists("xkjhsfjlsjf"));
	}*/
}
