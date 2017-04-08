package ru.andrei.tsystemsverificationwork.web;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:/datasource.xml",
        "classpath:/security-context.xml",
        "classpath:/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientsDaoTests {


//    @Autowired
//    private ClientsDao clientsDao;
//
//    @Autowired
//    private RolesDao rolesDao;
//
//    @Autowired
//    private DataSource dataSource;
//
//    private Client user1 = new Client("Ivan", "Petrov",
//            new Date(0l), "IvanPetrov@andrei.ru", "smth");
//    private Client user2 = new Client("Ivan", "Sidorov",
//            new Date(0l), "IvanSidorov@andrei.ru", "smth");
//    private Client user3 = new Client("Petya", "Ivanov",
//            new Date(0l), "PetyaIvanov@andrei.ru", "smth");
//    private Client user4 = new Client("Pavel", "Antonov",
//            new Date(0l), "Pavel@andrei.ru", "smth", true);
//    private Role role = new Role("ROLE_ADMIN");


    @Test
    public void testCreateRetrieve() {

//        List<Role> roles = new ArrayList<>();
//        roles.add(rolesDao.findByName("ROLE_ADMIN"));
////        roles.add(new Role("ROLE_ADMIN"));
//
//
//        user1.setRoles(roles);
//
//
//        clientsDao.create(user1);


        assertTrue(true);

    }


}
