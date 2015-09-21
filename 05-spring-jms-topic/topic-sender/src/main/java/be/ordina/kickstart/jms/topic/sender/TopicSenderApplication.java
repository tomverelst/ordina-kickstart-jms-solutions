package be.ordina.kickstart.jms.topic.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@EnableScheduling
public class TopicSenderApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TopicSenderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TopicSenderApplication.class);
    }

    @Component
    public static class Sender {

        @Autowired
        private JmsTemplate jmsTemplate;

        private AtomicLong counter = new AtomicLong(0L);

        @Scheduled(fixedRate = 1000L)
        public void sendMessage(){
            final Long count = counter.incrementAndGet();
            final String message = String.format("Message %d", count);

            LOG.info("Sending message \"{}\"", message);
            jmsTemplate.send("testTopic", session -> {
                final TextMessage msg = session.createTextMessage(message);
                msg.setLongProperty("count", count);
                return msg;
            });
        }

    }

}
