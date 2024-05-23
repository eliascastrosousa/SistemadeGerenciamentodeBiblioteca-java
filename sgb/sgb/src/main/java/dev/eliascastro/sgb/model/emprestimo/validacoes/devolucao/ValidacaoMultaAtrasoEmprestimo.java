package dev.eliascastro.sgb.model.emprestimo.validacoes.devolucao;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.model.emprestimo.DadosDevolucaoEmprestimoLivro;
import dev.eliascastro.sgb.model.emprestimo.Emprestimo;
import dev.eliascastro.sgb.model.emprestimo.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidacaoMultaAtrasoEmprestimo implements ValidacaoDevolucaoLivro{

    @Autowired
    private EmprestimoRepository repository;
    @Override
    public void validar(Long id) {

    }
}
