package dev.eliascastro.sgb.model.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.livro.Livro;
import dev.eliascastro.sgb.model.usuario.DadosDetalhamentoUsuario;
import dev.eliascastro.sgb.model.usuario.Usuario;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoEmprestimo(
        Long id,
        Long  idLivro,
        Long idAluno,
        LocalDate dataDevolucao ) {
    public DadosDetalhamentoEmprestimo(Emprestimo emprestimo){
        this(emprestimo.getId(), emprestimo.getAluno().getId(), emprestimo.getLivro().getId(), emprestimo.getDataDevolucao());
    }
}
