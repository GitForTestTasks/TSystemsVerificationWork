package ru.tsystemsverificationwork.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static junit.framework.TestCase.assertNotNull;



@ContextConfiguration(locations = "classpath:datasource.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectionTest  {

    @PersistenceContext
    private EntityManager transactionManager;


    @Test
    public void testHibernateConfiguration() {

        assertNotNull (transactionManager);
    }
}
