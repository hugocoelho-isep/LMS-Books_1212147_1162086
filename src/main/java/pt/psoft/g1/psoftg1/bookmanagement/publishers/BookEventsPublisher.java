package pt.psoft.g1.psoftg1.bookmanagement.publishers;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;

public interface BookEventsPublisher {

    void sendBookCreated(Book book);

    void sendBookUpdated(Book book, Long currentVersion);

    void sendBookDeleted(Book book, Long currentVersion);
}
