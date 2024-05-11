package dev.eliascastro.sgb.model.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.model.aluno.AlunoRepository;
import dev.eliascastro.sgb.model.emprestimo.DadosCadastroEmprestimoLivro;
import org.springframework.beans.factory.annotation.Autowired;

public interface ValidacaoEmprestimoLivro {
    void validar(DadosCadastroEmprestimoLivro dados);
}
