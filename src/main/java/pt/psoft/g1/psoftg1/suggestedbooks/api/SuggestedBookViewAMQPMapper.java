package pt.psoft.g1.psoftg1.suggestedbooks.api;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;

@Component
public class SuggestedBookViewAMQPMapper {

    public SuggestedBookViewAMQP toSuggestedBookViewAMQP(SuggestedBooks suggestedBooks) {
        SuggestedBookViewAMQP suggestedBookViewAMQP = new SuggestedBookViewAMQP();
        suggestedBookViewAMQP.setId(suggestedBooks.getId());
        suggestedBookViewAMQP.setTitle(suggestedBooks.getTitle());
        suggestedBookViewAMQP.setAuthor(suggestedBooks.getAuthors());
        suggestedBookViewAMQP.setGenre(suggestedBooks.getGenre());
        suggestedBookViewAMQP.setReaderId(suggestedBooks.getReaderId());
        suggestedBookViewAMQP.setSugestion(suggestedBooks.getSugestion());
        suggestedBookViewAMQP.setIsbn(suggestedBooks.getIsbn());
        suggestedBookViewAMQP.setVersion(suggestedBooks.getVersion());
        return suggestedBookViewAMQP;
    }
}