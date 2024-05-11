package dev.eliascastro.sgb.model.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.model.aluno.AlunoRepository;
import dev.eliascastro.sgb.model.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.model.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoLivroNaoDisponivel implements ValidacaoEmprestimoLivro{
    @Autowired
    private LivroRepository repository;
    @Override
    public void validar(DadosCadastroEmprestimoLivro dados) {
        if (!repository.existsByIdAndDisponivelTrue(dados.idLivro())){
            throw new ValidacaoException("Livro informado não está disponivel");
        }
    }
}
