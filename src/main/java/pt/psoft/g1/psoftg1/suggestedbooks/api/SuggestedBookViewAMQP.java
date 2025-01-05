package pt.psoft.g1.psoftg1.suggestedbooks.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SuggestedBookViewAMQP {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String readerId;
    private String sugestion;
    private Long version;
    private String isbn;

    public String getIsbn() {
        return isbn;
    }
}