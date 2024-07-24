package dev.eliascastro.sgb.application.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.livro.CategoriaLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroLivro(
        @NotBlank
        String titulo,

        @NotBlank
        String autor,

        @NotBlank
        @Pattern(regexp = "\\d{10,13}")
        String isbn,

        CategoriaLivro categoriaLivro
        ) implements Serializable {
}
