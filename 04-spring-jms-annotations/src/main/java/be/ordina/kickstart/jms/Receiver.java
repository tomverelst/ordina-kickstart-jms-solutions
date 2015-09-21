package be.ordina.kickstart.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Receiver {

    private List<String> messages = new ArrayList<>();

    @JmsListener(destination = "testQueue")
    public void receive(String message){
        messages.add(message);
    }

    public List<String> getReceivedMessages() {
        return messages;
    }
}
