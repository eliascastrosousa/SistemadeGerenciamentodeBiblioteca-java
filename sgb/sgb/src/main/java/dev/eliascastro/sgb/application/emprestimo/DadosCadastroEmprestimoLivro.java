package dev.eliascastro.sgb.application.emprestimo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroEmprestimoLivro(

        @JsonAlias({"isbn", "isbn_livro", "livro_isbn"})
        String isbn,
        @JsonAlias({"cpf", "cpf_aluno", "aluno_cpf"})
        String cpf
        ) {
}
