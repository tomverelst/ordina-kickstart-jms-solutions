package be.ordina.kickstart.jms;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmsApplication.class)
public class AsynchronousOnMessageTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    private TaskExecutor executor = new SimpleAsyncTaskExecutor();

    @Test
    public void testAsynchronousOnMessage(){
        executor.execute(() -> sender.sendMessage("Test message"));

        await().atMost(5L, TimeUnit.SECONDS).until(aMessageIsReceived());

        Assertions.assertThat(receiver.getReceivedMessages()).containsExactly("Test message");
    }

    private Callable<Boolean> aMessageIsReceived() {
        return () -> receiver.getReceivedMessages().size() > 0;
    }

}
