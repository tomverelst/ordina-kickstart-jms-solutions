package be.ordina.kickstart.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * Helper class for finding administered objects
 */
@Component
public class Destinations {

    @Autowired
    private JmsTemplate jmsTemplate;

    public Queue findQueue(Session session, String queueName) throws JMSException{
        return (Queue) findDestination(session, queueName, false);
    }

    public Topic findTopic(Session session, String topicName) throws JMSException{
        return (Topic) findDestination(session, topicName, true);
    }

    public Destination findDestination(final Session session, String destinationName, boolean pubSubDomain) throws JMSException{
        return jmsTemplate.getDestinationResolver().resolveDestinationName(session, destinationName, pubSubDomain);
    }
}
