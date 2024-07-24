/*
package dev.eliascastro.sgb.infra.emprestimo;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.application.emprestimo.DadosDetalhamentoEmprestimo;
import dev.eliascastro.sgb.infra.aluno.AlunoRepository;
import dev.eliascastro.sgb.infra.emprestimo.validacoes.devolucao.ValidacaoDevolucaoLivro;
import dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos.ValidacaoEmprestimoLivro;
import dev.eliascastro.sgb.infra.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoDeLivros {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private List<ValidacaoEmprestimoLivro> validacaoEmprestimoLivro;

    @Autowired
    private List<ValidacaoDevolucaoLivro> validacaoDevolucaoLivros;

    public DadosDetalhamentoEmprestimo emprestar(DadosCadastroEmprestimoLivro dados){

        if (dados.idAluno() != null && !alunoRepository.existsByIdAndAtivoTrue(dados.idAluno())){
            throw new ValidacaoException("Id do aluno informado não existe ou não está disponivel.");
        }

        if (dados.idLivro() == null ){
            throw new ValidacaoException("Id do livro informado não existe ou não disponivel.");
        }

        validacaoEmprestimoLivro.forEach(v -> v.validar(dados));

        var aluno = alunoRepository.findByCpf(dados. dados.idAluno()).get();
        var livro = livroRepository.findById(dados.idLivro()).get();
        var emprestimo = new Emprestimo(livro, aluno);

        livro.marcarEmprestimo();
        aluno.decrescentaAoLimiteEmprestimo();

        emprestimoRepository.save(emprestimo);
        return new DadosDetalhamentoEmprestimo(emprestimo);

    }

    public void devolucao(Long id) {
        if (!emprestimoRepository.existsById(id) ) {
            throw new ValidacaoException("Id do emprestimo informado não existe!");
        }

        validacaoDevolucaoLivros.forEach(v -> v.validar(id));

        var emprestimo = emprestimoRepository.getReferenceById(id);
        var livro = livroRepository.getReferenceById(emprestimo.getLivro().getId());
        var aluno = alunoRepository.getReferenceById(emprestimo.getAluno().getId());

        emprestimo.verificaMulta();

        if (emprestimo.getMulta() > 0.0) {
            aluno.adicionarMulta(emprestimo.getMulta());
        }else {
            aluno.acrescentaAoLimiteEmprestimo();
        }
        livro.desmarcarEmprestimo();
        emprestimo.arquivar();








    }


}
*/