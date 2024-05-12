package dev.eliascastro.sgb.model.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.livro.Livro;
import dev.eliascastro.sgb.model.usuario.DadosDetalhamentoUsuario;
import dev.eliascastro.sgb.model.usuario.Usuario;

import java.time.LocalDate;
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
