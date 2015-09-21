package be.ordina.kickstart.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private Destinations destinations;

    @Autowired
    private ConnectionFactory connectionFactory;

    private List<String> messages = new CopyOnWriteArrayList<>();

    public void receiveMessageJMSMessageSynchronously() {
        try (final Connection connection = connectionFactory.createConnection()) {

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final Queue queue = destinations.findQueue(session, "testQueue");

            MessageConsumer messageConsumer = session.createConsumer(queue);

            connection.start();

            TextMessage textMessage = (TextMessage) messageConsumer.receive();

            LOG.info("Received text: {}", textMessage.getText());

            messages.add(textMessage.getText());

        } catch (JMSException ex){
            LOG.error("JMS exception occurred", ex);
        }
    }

    public List<String> getReceivedMessages() {
        return new ArrayList<>(messages);
    }
}
