package be.ordina.kickstart.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class Sender {

    private static Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private ConnectionFactory jmsConnectionFactory;

    @Autowired
    private Destinations destinations;

    public void sendMessage(String message){
        try (final Connection connection = jmsConnectionFactory.createConnection()){
            try(final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)){

                final Queue queue = destinations.findQueue(session, "testQueue");

                final MessageProducer messageProducer = session.createProducer(queue);

                final TextMessage textMessage = session.createTextMessage(message);

                LOG.info("Sending message {}", textMessage.getText());

                messageProducer.send(textMessage);
            }
        } catch (JMSException ex) {
            LOG.error("JMS exception occured", ex);
        }
    }

}
