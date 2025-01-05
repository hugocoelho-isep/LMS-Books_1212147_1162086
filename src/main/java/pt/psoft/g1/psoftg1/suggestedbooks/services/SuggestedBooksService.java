package pt.psoft.g1.psoftg1.suggestedbooks.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.services.BookService;
import pt.psoft.g1.psoftg1.suggestedbooks.api.SuggestedBookViewAMQP;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;
import pt.psoft.g1.psoftg1.suggestedbooks.publishers.SuggestedBookEventsPublisher;
import pt.psoft.g1.psoftg1.suggestedbooks.repositories.SuggestedBooksRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuggestedBooksService {

    private final SuggestedBooksRepository suggestedBooksRepository;
    private final BookService bookService;
    private final SuggestedBookEventsPublisher suggestedBookEventsPublisher;

    @Transactional
    public SuggestedBooks suggestBook(String isbn, String readerId, String sugestion) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        Optional<SuggestedBooks> existingSuggestedBook = suggestedBooksRepository.findByIsbn(isbn);
        if (existingSuggestedBook.isPresent()) {
            return existingSuggestedBook.get();
        }

        Book book = bookService.findByIsbn(isbn);
        SuggestedBooks suggestedBook = new SuggestedBooks(
                book.getTitle().toString(),
                book.getAuthors().stream().map(author -> author.getName()).collect(Collectors.joining(", ")),
                book.getGenre().getGenre(),
                readerId,
                sugestion,
                isbn
        );
        SuggestedBooks savedSuggestedBook = suggestedBooksRepository.save(suggestedBook);
        if (savedSuggestedBook != null) {
            suggestedBookEventsPublisher.sendSuggestedBookCreated(savedSuggestedBook);
        }
        return savedSuggestedBook;
    }


    public SuggestedBooks create(SuggestedBookViewAMQP suggestedBookViewAMQP) {
        String isbn = suggestedBookViewAMQP.getIsbn();
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        Optional<SuggestedBooks> existingSuggestedBook = suggestedBooksRepository.findByIsbn(isbn);
        if (existingSuggestedBook.isPresent()) {
            return existingSuggestedBook.get();
        }

        SuggestedBooks suggestedBook = new SuggestedBooks(
                suggestedBookViewAMQP.getTitle(),
                suggestedBookViewAMQP.getAuthor(),
                suggestedBookViewAMQP.getGenre(),
                suggestedBookViewAMQP.getReaderId(),
                suggestedBookViewAMQP.getSugestion(),
                isbn
        );
        return suggestedBooksRepository.save(suggestedBook);
    }
}