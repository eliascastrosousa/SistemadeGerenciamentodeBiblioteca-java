package dev.eliascastro.sgb.model.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoLivro(
        String titulo,
        String autor,
        String genero) {
}
