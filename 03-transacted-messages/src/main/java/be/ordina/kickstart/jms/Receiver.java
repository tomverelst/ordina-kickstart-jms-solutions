package be.ordina.kickstart.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.List;

@Component
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private Destinations destinations;

    @Autowired
    private ConnectionFactory connectionFactory;

    private Connection connection;

    private MessageCapturingMessageListener listener = new MessageCapturingMessageListener();

    @PostConstruct
    public void start() throws JMSException {
        LOG.info("Starting Receiver");

        this.connection = connectionFactory.createConnection();

        final Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

        final Queue queue = destinations.findQueue(session, "testQueue");

        MessageConsumer messageConsumer = session.createConsumer(queue);

        messageConsumer.setMessageListener(listener);

        connection.start();

        LOG.info("Started Receiver");
    }

    @PreDestroy
    public void end() throws JMSException{
        if(connection != null) {
            this.connection.close();
        }
    }

    public List<String> getReceivedMessages(){
        return new ArrayList<>(listener.getMessages());
    }
}
