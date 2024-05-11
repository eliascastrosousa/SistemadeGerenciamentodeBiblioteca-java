package dev.eliascastro.sgb.model.emprestimo;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.model.aluno.AlunoRepository;
import dev.eliascastro.sgb.model.emprestimo.validacoes.emprestimos.ValidacaoEmprestimoLivro;
import dev.eliascastro.sgb.model.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public DadosDetalhamentoEmprestimo emprestar(DadosCadastroEmprestimoLivro dados){

        if (dados.idAluno() != null && !alunoRepository.existsByIdAndAtivoTrue(dados.idAluno())){
            throw new ValidacaoException("Id do aluno informado não existe");
        }

        if (dados.idLivro() == null ){
            throw new ValidacaoException("Id do livro informado não existe");
        }

        validacaoEmprestimoLivro.forEach(v -> v.validar(dados));

        var aluno = alunoRepository.findById(dados.idAluno()).get();
        var livro = livroRepository.findById(dados.idLivro()).get();
        var emprestimo = new Emprestimo(livro, aluno);

        livro.marcarEmprestimo();
        aluno.decrescentaAoLimiteEmprestimo();

        emprestimoRepository.save(emprestimo);
        return new DadosDetalhamentoEmprestimo(emprestimo);

    }

    public void devolucao(Long id) {
        var emprestimo = emprestimoRepository.findById(id);
        var livro = livroRepository.findById(emprestimo.get().getLivro().getId());
        var aluno = alunoRepository.findById(emprestimo.get().getAluno().getId());
        livro.get().desmarcarEmprestimo();
        aluno.get().acrescentaAoLimiteEmprestimo();
        emprestimo.get().arquivar();

    }

}
