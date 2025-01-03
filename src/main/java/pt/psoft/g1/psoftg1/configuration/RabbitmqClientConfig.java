package pt.psoft.g1.psoftg1.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pt.psoft.g1.psoftg1.bookmanagement.api.BookEventRabbitmqReceiver;
import pt.psoft.g1.psoftg1.bookmanagement.services.BookService;
import pt.psoft.g1.psoftg1.shared.model.BookEvents;
import pt.psoft.g1.psoftg1.shared.model.SuggestedBooksEvents;

@Profile("!test")
@Configuration
public class RabbitmqClientConfig {

    @Bean(name = "bookDirectExchange")
    public DirectExchange direct() {
        return new DirectExchange("LMS.books");
    }

    private static class ReceiverConfig {

        @Bean(name = "autoDeleteQueue_Book_Created")
        public Queue autoDeleteQueue_Book_Created() {
            System.out.println("autoDeleteQueue_Book_Created created!");
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Book_Updated() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Book_Deleted() {
            return new AnonymousQueue();
        }

        @Bean(name = "binding1_books")
        public Binding binding1(@Qualifier("bookDirectExchange") DirectExchange direct,
                                @Qualifier("autoDeleteQueue_Book_Created") Queue autoDeleteQueue_Book_Created) {
            return BindingBuilder.bind(autoDeleteQueue_Book_Created)
                    .to(direct)
                    .with(BookEvents.BOOK_CREATED);
        }

        @Bean(name = "binding2_books")
        public Binding binding2(@Qualifier("bookDirectExchange") DirectExchange direct,
                                Queue autoDeleteQueue_Book_Updated) {
            return BindingBuilder.bind(autoDeleteQueue_Book_Updated)
                    .to(direct)
                    .with(BookEvents.BOOK_UPDATED);
        }

        @Bean(name = "binding3_books")
        public Binding binding3(@Qualifier("bookDirectExchange") DirectExchange direct,
                                Queue autoDeleteQueue_Book_Deleted) {
            return BindingBuilder.bind(autoDeleteQueue_Book_Deleted)
                    .to(direct)
                    .with(BookEvents.BOOK_DELETED);
        }

        @Bean(name = "bookReceiver")
        public BookEventRabbitmqReceiver receiver(BookService bookService, @Qualifier("autoDeleteQueue_Book_Created") Queue autoDeleteQueue_Book_Created) {
            return new BookEventRabbitmqReceiver(bookService);
        }

        @Bean(name = "autoDeleteQueue_Suggestion_Created")
        public Queue autoDeleteQueue_Suggestion_Created() {
            System.out.println("autoDeleteQueue_Suggestion_Created created!");
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Suggestion_Updated() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue_Suggestion_Deleted() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding4(@Qualifier("bookDirectExchange") DirectExchange direct,
                                @Qualifier("autoDeleteQueue_Suggestion_Created") Queue autoDeleteQueue_Suggestion_Created) {
            return BindingBuilder.bind(autoDeleteQueue_Suggestion_Created)
                    .to(direct)
                    .with(SuggestedBooksEvents.SUGGESTION_CREATED);
        }
    }
}