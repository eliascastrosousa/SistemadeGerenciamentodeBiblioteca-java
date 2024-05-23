package dev.eliascastro.sgb.model.emprestimo.validacoes.devolucao;

import dev.eliascastro.sgb.model.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.model.emprestimo.DadosDevolucaoEmprestimoLivro;
import dev.eliascastro.sgb.model.emprestimo.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ValidacaoDevolucaoLivro {

    void validar(Long id);
}
