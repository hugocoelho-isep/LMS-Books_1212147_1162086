package pt.psoft.g1.psoftg1.suggestedbooks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
public class SuggestedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String authors;
    private String genre;
    private String readerId;
    private String sugestion;
    private String isbn;

    // Constructor with all parameters
    public SuggestedBooks(String title, String authors, String genre, String readerId, String sugestion, String isbn) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.readerId = readerId;
        this.sugestion = sugestion;
        this.isbn = isbn;
    }


    public SuggestedBooks() {
    }

    // Getter for authors
    public String getAuthors() {
        return authors;
    }

    public Long getVersion() {
        return 1L;
    }
}