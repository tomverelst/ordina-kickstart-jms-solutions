package be.ordina.kickstart.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message){
        jmsTemplate.send("testQueue", session -> {
            return session.createTextMessage(message);
        });
    }

}
