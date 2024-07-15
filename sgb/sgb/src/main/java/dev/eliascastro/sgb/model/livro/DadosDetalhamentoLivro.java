package dev.eliascastro.sgb.model.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoLivro(String titulo, String autor, String genero) implements Serializable {
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getGenero());
    }
}
