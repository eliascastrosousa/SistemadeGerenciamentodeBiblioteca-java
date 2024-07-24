package dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.infra.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoLivroNaoDisponivel implements ValidacaoEmprestimoLivro{
    @Autowired
    private LivroRepository repository;
    @Override
    public void validar(DadosCadastroEmprestimoLivro dados) {
        if (repository.existsByIsbnAndDisponivelFalse(dados.isbn())){
            throw new ValidacaoException("Livro informado não está disponivel");
        }
        if (!repository.existsByIsbn(dados.isbn())){
            throw new ValidacaoException("Livro informado não existe");
        }
        if (repository.existsByIsbnAndAtivoFalse(dados.isbn())){
            throw new ValidacaoException("Livro informado não pode ser utilizado no momento.");
        }

    }
}
