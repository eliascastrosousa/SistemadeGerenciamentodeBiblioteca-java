package dev.eliascastro.sgb.model.emprestimo;

import dev.eliascastro.sgb.model.aluno.Aluno;
import dev.eliascastro.sgb.model.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "emprestimos")
@Entity(name = "Emprestimo")
@EqualsAndHashCode(of = "id")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private LocalDate dataEmprestimo = LocalDate.now();
    private LocalDate dataDevolucao = LocalDate.now().plusDays(7);

    private boolean ativo = true;

    private Double multa;

    public Emprestimo(DadosCadastroEmprestimoLivro dados) {

    }
    public Emprestimo(Livro livro, Aluno aluno, LocalDate dataEmprestimo, LocalDate dataDevolucao){
        this.livro = livro;
        this.aluno = aluno;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        multa = 0.0;
    }
    public Emprestimo( Livro livro, Aluno aluno) {
        this.livro = livro;
        this.aluno = aluno;
        multa = 0.0;
    }

    public void verificaMulta() {
        var dias = LocalDate.now().compareTo(this.dataDevolucao);
        if (dias > 0) {
            var multaDaDevolucao = 0.50 * dias;
            multa += multaDaDevolucao;
        }
    }

    public void arquivar() {
        ativo = false;
    }
}
