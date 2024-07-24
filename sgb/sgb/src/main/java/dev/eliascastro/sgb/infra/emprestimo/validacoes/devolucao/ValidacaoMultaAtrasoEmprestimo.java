package dev.eliascastro.sgb.infra.emprestimo.validacoes.devolucao;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.infra.emprestimo.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMultaAtrasoEmprestimo implements ValidacaoDevolucaoLivro{

    @Autowired
    private EmprestimoRepository repository;
    @Override
    public void validar(Long id) {
        var emprestimo = repository.findById(id);
        if (emprestimo.isPresent()){
            emprestimo.get().verificaMulta();
            emprestimo.get().getAluno().decrescentarLimiteDeEmprestimo();
            emprestimo.get().getLivro().setDisponivel(false);
        }else {
            throw new ValidacaoException("Emprestimo informado não existe.");
        }
    }
}
