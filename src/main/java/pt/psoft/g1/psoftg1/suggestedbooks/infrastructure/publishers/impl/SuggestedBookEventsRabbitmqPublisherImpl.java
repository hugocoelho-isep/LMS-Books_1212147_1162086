package pt.psoft.g1.psoftg1.suggestedbooks.infrastructure.publishers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.suggestedbooks.api.SuggestedBookViewAMQP;
import pt.psoft.g1.psoftg1.suggestedbooks.api.SuggestedBookViewAMQPMapper;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;
import pt.psoft.g1.psoftg1.suggestedbooks.publishers.SuggestedBookEventsPublisher;
import pt.psoft.g1.psoftg1.shared.model.SuggestedBooksEvents;

@Service
@RequiredArgsConstructor
public class SuggestedBookEventsRabbitmqPublisherImpl implements SuggestedBookEventsPublisher {

    @Autowired
    private RabbitTemplate template;
    @Qualifier("bookDirectExchange")
    @Autowired
    private DirectExchange direct;
    @Autowired
    private final SuggestedBookViewAMQPMapper suggestedBookViewAMQPMapper;

    @Override
    public SuggestedBookViewAMQP sendSuggestedBookCreated(SuggestedBooks suggestedBook) {
        return sendSuggestedBookEvent(suggestedBook, 1L, SuggestedBooksEvents.SUGGESTION_CREATED);
    }

//    @Override
//    public SuggestedBookViewAMQP sendSuggestedBookUpdated(SuggestedBooks suggestedBook, Long currentVersion) {
//        return sendSuggestedBookEvent(suggestedBook, currentVersion, SuggestedBooksEvents.SUGGESTION_UPDATED);
//    }
//
//    @Override
//    public SuggestedBookViewAMQP sendSuggestedBookDeleted(SuggestedBooks suggestedBook, Long currentVersion) {
//        return sendSuggestedBookEvent(suggestedBook, currentVersion, SuggestedBooksEvents.SUGGESTION_DELETED);
//    }

    private SuggestedBookViewAMQP sendSuggestedBookEvent(SuggestedBooks suggestedBook, Long currentVersion, String suggestedBookEventType) {

        System.out.println("Send Suggested Book event to AMQP Broker: " + suggestedBook.getTitle());

        try {
            SuggestedBookViewAMQP suggestedBookViewAMQP = suggestedBookViewAMQPMapper.toSuggestedBookViewAMQP(suggestedBook);
            suggestedBookViewAMQP.setVersion(currentVersion);

            ObjectMapper objectMapper = new ObjectMapper();
            String suggestedBookViewAMQPinString = objectMapper.writeValueAsString(suggestedBookViewAMQP);

            this.template.convertAndSend(direct.getName(), suggestedBookEventType, suggestedBookViewAMQPinString);

            return suggestedBookViewAMQP;
        } catch (Exception ex) {
            System.out.println(" [x] Exception sending suggested book event: '" + ex.getMessage() + "'");

            return null;
        }
    }
}