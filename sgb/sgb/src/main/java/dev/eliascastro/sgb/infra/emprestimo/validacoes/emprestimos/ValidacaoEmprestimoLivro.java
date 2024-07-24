package dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;

public interface ValidacaoEmprestimoLivro {
    void validar(DadosCadastroEmprestimoLivro dados);
}
