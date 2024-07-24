package dev.eliascastro.sgb.application.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.emprestimo.Emprestimo;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoEmprestimo(
        Long id,
        String nome,
        String titulo,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao ) {
    public DadosDetalhamentoEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getId(), emprestimo.getAluno().getNome(), emprestimo.getLivro().getTitulo(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}
