package pt.psoft.g1.psoftg1.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorEventRabbitmqReceiver;
import pt.psoft.g1.psoftg1.authormanagement.services.AuthorService;
import pt.psoft.g1.psoftg1.shared.model.AuthorEvents;


@Profile("!test")
@Configuration
public class RabbitmqAuthorConfig {
    @Bean(name = "authorDirectExchange")
    public DirectExchange direct() {
        return new DirectExchange("LMS.authors");
    }

    private static class ReceiverConfig {

        @Bean(name = "autoDeleteQueue_Author_Created")
        public Queue autoDeleteQueue_Author_Created() {

            System.out.println("autoDeleteQueue_Author_Created created!");
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Author_Updated() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Author_Deleted() {
            return new AnonymousQueue();
        }

        @Bean(name = "binding1_authors")
        public Binding binding1(@Qualifier("authorDirectExchange") DirectExchange direct,
                                @Qualifier("autoDeleteQueue_Author_Created") Queue autoDeleteQueue_Author_Created) {
            return BindingBuilder.bind(autoDeleteQueue_Author_Created)
                    .to(direct)
                    .with(AuthorEvents.AUTHOR_CREATED);
        }

        @Bean(name = "binding2_authors")
        public Binding binding2(@Qualifier("authorDirectExchange") DirectExchange direct,
                                Queue autoDeleteQueue_Author_Updated) {
            return BindingBuilder.bind(autoDeleteQueue_Author_Updated)
                    .to(direct)
                    .with(AuthorEvents.AUTHOR_UPDATED);
        }

        @Bean(name = "binding3_authors")
        public Binding binding3(@Qualifier("authorDirectExchange") DirectExchange direct,
                                Queue autoDeleteQueue_Author_Deleted) {
            return BindingBuilder.bind(autoDeleteQueue_Author_Deleted)
                    .to(direct)
                    .with(AuthorEvents.AUTHOR_DELETED);
        }

        @Bean(name = "authorReceiver")
        public AuthorEventRabbitmqReceiver receiver(AuthorService AuthorService, @Qualifier("autoDeleteQueue_Author_Created") Queue autoDeleteQueue_Author_Created) {
            return new AuthorEventRabbitmqReceiver(AuthorService);
        }
    }
}
