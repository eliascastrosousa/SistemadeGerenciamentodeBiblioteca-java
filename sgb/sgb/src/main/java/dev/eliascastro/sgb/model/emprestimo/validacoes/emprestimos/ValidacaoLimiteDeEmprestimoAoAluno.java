package dev.eliascastro.sgb.model.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.model.aluno.AlunoRepository;
import dev.eliascastro.sgb.model.emprestimo.DadosCadastroEmprestimoLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoLimiteDeEmprestimoAoAluno implements ValidacaoEmprestimoLivro{
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void validar(DadosCadastroEmprestimoLivro dados) {
        var limiteDoAluno = alunoRepository.findById(dados.idAluno());

        if (limiteDoAluno.get().getLimiteLivros() <= 0){
            throw new ValidacaoException("Aluno ja usou toda sua cota de emprestimos");
        }
    }
}
