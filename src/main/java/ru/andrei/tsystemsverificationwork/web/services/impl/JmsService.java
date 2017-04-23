package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;


@Service("jmsService")
public class JmsService {


//    @Resource(mappedName = "java:/ConnectionFactory")
//    private ConnectionFactory connectionFactory;
//
//    @Resource(mappedName = "java:/jms/queue/statistics")
//    private Queue queue;

    public void submit(String text) throws JMSException {
//        System.out.println("Sending " + text);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//
//        Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
//
//        MessageProducer messageProducer = session.createProducer(queue);
//        TextMessage message = session.createTextMessage("Hello from main");
//
//        messageProducer.send(message);
//
//        connection.close();

    }
}
