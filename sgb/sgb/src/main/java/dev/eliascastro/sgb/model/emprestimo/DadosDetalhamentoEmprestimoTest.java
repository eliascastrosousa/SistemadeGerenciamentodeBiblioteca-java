package dev.eliascastro.sgb.model.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoEmprestimoTest(
        Long idLivro,
        Long idAluno
         ) {
    public DadosDetalhamentoEmprestimoTest(Emprestimo emprestimo){
        this(emprestimo.getAluno().getId(), emprestimo.getLivro().getId());
    }
}
