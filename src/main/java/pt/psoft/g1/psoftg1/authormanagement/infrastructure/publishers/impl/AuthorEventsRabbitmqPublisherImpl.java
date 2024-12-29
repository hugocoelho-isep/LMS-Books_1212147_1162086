package pt.psoft.g1.psoftg1.authormanagement.infrastructure.publishers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorViewAMQP;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorViewAMQPMapper;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.publishers.AuthorEventsPublisher;
import pt.psoft.g1.psoftg1.bookmanagement.api.BookViewAMQPMapper;
import pt.psoft.g1.psoftg1.shared.model.AuthorEvents;

@Service
public class AuthorEventsRabbitmqPublisherImpl implements AuthorEventsPublisher {

    private RabbitTemplate template;
    private DirectExchange direct;
    private final AuthorViewAMQPMapper authorViewAMQPMapper;

    public AuthorEventsRabbitmqPublisherImpl(
            RabbitTemplate template,
            @Qualifier("authorDirectExchange") DirectExchange direct,
            AuthorViewAMQPMapper authorViewAMQPMapper) {
        this.template = template;
        this.direct = direct;
        this.authorViewAMQPMapper = authorViewAMQPMapper;
    }

    @Override
    public void sendAuthorCreated(Author author) {
        sendAuthorEvent(author, author.getVersion(), AuthorEvents.AUTHOR_CREATED);
    }

    @Override
    public void sendAuthorUpdated(Author author, Long currentVersion) {
        sendAuthorEvent(author, currentVersion, AuthorEvents.AUTHOR_UPDATED);
    }


    public void sendAuthorEvent(Author author, Long currentVersion, String bookEventType) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            AuthorViewAMQP authorViewAMQP = authorViewAMQPMapper.toAuthorViewAMQP(author);
            authorViewAMQP.setVersion(currentVersion);

            String jsonString = objectMapper.writeValueAsString(authorViewAMQP);

            this.template.convertAndSend(direct.getName(), bookEventType, jsonString);

            System.out.println(" [x] Sent '" + jsonString + "'");
        }
        catch( Exception ex ) {
            System.out.println(" [x] Exception sending book event: '" + ex.getMessage() + "'");
        }
    }
}
