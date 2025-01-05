package pt.psoft.g1.psoftg1.suggestedbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;

import java.util.Optional;

public interface SuggestedBooksRepository extends JpaRepository<SuggestedBooks, Long> {
    Optional<SuggestedBooks> findByIsbn(String isbn);

}