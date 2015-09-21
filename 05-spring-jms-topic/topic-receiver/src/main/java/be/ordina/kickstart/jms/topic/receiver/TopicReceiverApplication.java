package be.ordina.kickstart.jms.topic.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@SpringBootApplication
public class TopicReceiverApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TopicReceiverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TopicReceiverApplication.class);
    }

    @Component
    public static class Receiver {

        @JmsListener(destination = "testTopic", selector = "count > 20")
        public void receiveMessage(TextMessage message) throws JMSException{
            LOG.info("Received message: {}", message.getText());
        }

    }

}
