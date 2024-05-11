package dev.eliascastro.sgb.model.emprestimo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroEmprestimoLivro(

        @JsonAlias({"idLivro", "id_livro", "livro_id"})
        Long idLivro,
        @JsonAlias({"idAluno", "id_aluno", "aluno_id"})
        Long idAluno
        ) {
}
