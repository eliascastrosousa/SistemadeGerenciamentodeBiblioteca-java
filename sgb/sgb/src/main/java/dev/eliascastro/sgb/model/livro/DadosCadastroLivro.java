package dev.eliascastro.sgb.model.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroLivro(
        String titulo,
        String autor,
        String isbn,
        String genero) {
}
