package pt.psoft.g1.psoftg1;

import org.mockito.Mockito;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pt.psoft.g1.psoftg1.bookmanagement.api.BookEventRabbitmqReceiver;

@TestConfiguration
public class TestConfig {

    @Bean(name = "autoDeleteQueue_Book_Created")
    public Queue testQueueCreated() {
        return new Queue("testQueueCreated");
    }

    @Bean(name = "autoDeleteQueue_Book_Updated")
    public Queue testQueueUpdated() {
        return new Queue("testQueueUpdated");
    }

    @Bean(name = "autoDeleteQueue_Book_Deleted")
    public Queue testQueueDeleted() {
        return new Queue("testQueueDeleted");
    }

    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange("testExchange");
    }
}