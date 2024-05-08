package dev.eliascastro.sgb.model.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoLivro(String titulo, String autor, String genero) {
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getGenero());
    }
}
