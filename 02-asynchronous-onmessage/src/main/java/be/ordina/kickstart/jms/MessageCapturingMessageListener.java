package be.ordina.kickstart.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This message listener captures all received messages in a List.
 */
public class MessageCapturingMessageListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageCapturingMessageListener.class);

    private List<String> messages = new CopyOnWriteArrayList<>();

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void onMessage(Message message) {
        LOG.info("Received message");

        try {
            if (message instanceof TextMessage) {
                this.messages.add(((TextMessage) message).getText());
            }
        } catch(JMSException ex){
            LOG.error("JMS exception", ex);
        }
    }
}
