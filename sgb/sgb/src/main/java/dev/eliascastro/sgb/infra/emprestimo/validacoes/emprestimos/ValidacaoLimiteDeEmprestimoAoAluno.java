package dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.infra.aluno.AlunoRepository;
import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoLimiteDeEmprestimoAoAluno implements ValidacaoEmprestimoLivro{
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void validar(DadosCadastroEmprestimoLivro dados) {
        var aluno = alunoRepository.findAtivoTrueByCpf(dados.cpf());
        if (aluno.isPresent()) {
            if (aluno.get().getLimiteLivros() <= 0){
                throw new ValidacaoException("Aluno ja usou toda sua cota de emprestimos");
            }
        }else {
            throw new ValidacaoException("Aluno não encontrado.");
        }

    }
}
