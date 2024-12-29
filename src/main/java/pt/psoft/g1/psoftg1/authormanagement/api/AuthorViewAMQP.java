package pt.psoft.g1.psoftg1.authormanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Schema(description = "A Lending")
public class AuthorViewAMQP {
    @NotNull
    private Long authorNumber;
    @NotNull
    private Long version;
    @NotNull
    private String name;
    @NotNull
    private String bio;

    private String photo;
}
