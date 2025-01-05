package pt.psoft.g1.psoftg1.suggestedbooks.publishers;

import pt.psoft.g1.psoftg1.suggestedbooks.api.SuggestedBookViewAMQP;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;

public interface SuggestedBookEventsPublisher {

    SuggestedBookViewAMQP sendSuggestedBookCreated(SuggestedBooks suggestedBook);

    //SuggestedBookViewAMQP sendSuggestedBookUpdated(SuggestedBooks suggestedBook, Long currentVersion);

    //SuggestedBookViewAMQP sendSuggestedBookDeleted(SuggestedBooks suggestedBook, Long currentVersion);
}