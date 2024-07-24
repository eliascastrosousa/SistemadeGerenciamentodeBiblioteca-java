package dev.eliascastro.sgb.application.livro;

import dev.eliascastro.sgb.infra.livro.CategoriaLivro;

import java.io.Serializable;

public record DadosAtualizacaoLivro(
        String titulo,
        String autor,
        CategoriaLivro categoriaLivro) implements Serializable {
}
