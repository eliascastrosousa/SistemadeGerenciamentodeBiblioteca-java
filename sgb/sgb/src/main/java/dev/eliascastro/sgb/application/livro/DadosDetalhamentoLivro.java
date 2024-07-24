package dev.eliascastro.sgb.application.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.livro.CategoriaLivro;
import dev.eliascastro.sgb.infra.livro.Livro;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoLivro(String titulo, String autor, CategoriaLivro categoriaLivro){
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getTitulo(), livro.getAutor(), livro.getCategoriaLivro());
    }
}
