package pt.psoft.g1.psoftg1.suggestedbooks.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pt.psoft.g1.psoftg1.suggestedbooks.model.SuggestedBooks;
import pt.psoft.g1.psoftg1.suggestedbooks.services.SuggestedBooksService;

@RestController
@RequestMapping("/api/suggested-books")
@RequiredArgsConstructor
public class SuggestedBooksController {

    private final SuggestedBooksService suggestedBooksService;

    @PostMapping("/isbn")
    public ResponseEntity<SuggestedBooks> suggestBookByIsbn(@RequestBody SuggestedBooksRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String readerId = authentication.getName();

        SuggestedBooks suggestedBook = suggestedBooksService.suggestBook(
                request.getIsbn(),
                readerId,
                request.getSugestion()
        );
        return ResponseEntity.ok(suggestedBook);
    }
}