package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Service responsible for java messages
 */
@Service("jmsService")
@Lazy
public class JmsService {

    /**
     * Connection factory to built-in artemis activemq
     */
    private ConnectionFactory connectionFactory;

    /**
     * Created queue in in activemq
     */
    private Queue queue;

    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(JmsService.class);

    @Autowired
    public JmsService(ConnectionFactory connectionFactory, Queue queue) {
        this.connectionFactory = connectionFactory;
        this.queue = queue;
    }

    /**
     * Submits text message in queue
     *
     * @param text text message
     * @throws JMSException message exception
     */
    @DependsOn(value = {"connectionFactory", "queue"})
    public void submit(String text) throws JMSException {

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        MessageProducer messageProducer = session.createProducer(queue);
        TextMessage message = session.createTextMessage(text);

        messageProducer.send(message);
        log.info("Webservice update has been forced");

        connection.close();

    }
}
