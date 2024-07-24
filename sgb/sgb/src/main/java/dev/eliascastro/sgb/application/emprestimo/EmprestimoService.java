package dev.eliascastro.sgb.application.emprestimo;

import dev.eliascastro.sgb.ValidacaoException;
import dev.eliascastro.sgb.infra.aluno.AlunoRepository;
import dev.eliascastro.sgb.infra.emprestimo.Emprestimo;
import dev.eliascastro.sgb.infra.emprestimo.EmprestimoRepository;
import dev.eliascastro.sgb.infra.emprestimo.validacoes.devolucao.ValidacaoDevolucaoLivro;
import dev.eliascastro.sgb.infra.emprestimo.validacoes.emprestimos.ValidacaoEmprestimoLivro;
import dev.eliascastro.sgb.infra.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {


    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private List<ValidacaoDevolucaoLivro> validacaoDevolucaoLivros;

    @Autowired
    private List<ValidacaoEmprestimoLivro> validacaoEmprestimoLivros;

    public Emprestimo cadastrarEmprestimo(DadosCadastroEmprestimoLivro dados) {

        validacaoEmprestimoLivros.forEach(v-> v.validar(dados));

        var aluno = alunoRepository.findByCpf(dados.cpf());
        var livro = livroRepository.findByIsbn(dados.isbn());
        var emprestimo = new Emprestimo(livro, aluno);
        aluno.decrescentarLimiteDeEmprestimo();
        livro.marcarEmprestimo();
        emprestimoRepository.save(emprestimo);
        return emprestimo;
    }

    public List<DadosDetalhamentoEmprestimo> listarEmprestimos(Pageable paginacao){
        Page<Emprestimo> emprestimos = emprestimoRepository.findAllByAtivoTrue(paginacao);
        return emprestimos.stream().map(this::converteDadosDetalhados).collect(Collectors.toList());
    }

    public DadosDetalhamentoEmprestimo buscarEmprestimo(Long id) {
        var emprestimo = emprestimoRepository.findById(id);
        return emprestimo.map(this::converteDadosDetalhados).orElse(null);
    }

    public String devolucaoEmprestimo(Long id) {

        if (!emprestimoRepository.existsByIdAndAtivoTrue(id) ) {
            throw new ValidacaoException("Id do emprestimo informado não existe!");
        }
        var emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()){
            emprestimo.get().verificaMulta();
            emprestimo.get().getAluno().acrescentarLimiteDeEmprestimo();
            emprestimo.get().getLivro().desmarcarEmprestimo();
            emprestimo.get().arquivar();
            var multa = emprestimo.get().getMulta();
            if (multa > 0.0){
                emprestimo.get().getAluno().adicionarMulta(multa);
                return "Devolucao realizada com sucesso! Atraso da entrega gerou multa de R$"
                        + multa + " adicionado ao saldo do Aluno na biblioteca. "  ;
            }
        }
        return null;
    }


    private DadosCadastroEmprestimoLivro converteDados(Emprestimo emprestimo) {
        return new DadosCadastroEmprestimoLivro(
                emprestimo.getLivro().getIsbn(),
                emprestimo.getAluno().getCpf()
        );
    }

    private DadosDetalhamentoEmprestimo converteDadosDetalhados(Emprestimo emprestimo) {
        return new DadosDetalhamentoEmprestimo(
                emprestimo.getId(),
                emprestimo.getAluno().getNome(),
                emprestimo.getLivro().getTitulo(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }



}
