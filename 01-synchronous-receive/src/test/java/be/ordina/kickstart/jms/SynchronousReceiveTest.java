package be.ordina.kickstart.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmsApplication.class)
public class SynchronousReceiveTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    private TaskExecutor executor = new SimpleAsyncTaskExecutor();

    @Test
    public void testSynchronousReceive(){
        executor.execute(() -> sender.sendMessage("Test message"));

        await().atMost(5L, TimeUnit.SECONDS).until(receiver::receiveMessageJMSMessageSynchronously);

        assertThat(receiver.getReceivedMessages()).containsExactly("Test message");
    }

}
