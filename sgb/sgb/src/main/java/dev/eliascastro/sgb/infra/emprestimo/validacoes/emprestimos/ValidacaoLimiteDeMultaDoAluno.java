package dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.infra.aluno.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoLimiteDeMultaDoAluno implements ValidacaoEmprestimoLivro{
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void validar(DadosCadastroEmprestimoLivro dados) {
        var aluno = alunoRepository.findAtivoTrueByCpf(dados.cpf());
        if (aluno.isPresent()) {
            if (aluno.get().getMulta()  > 50.0){
                throw new ValidacaoException("Aluno suspenso na plataforma por excesso de multa, verificar regularização com administração.");
            }
        }else {
            throw new ValidacaoException("Aluno não encontrado.");
        }
    }
}
