package dev.eliascastro.sgb.infra.emprestimo;

import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.infra.aluno.Aluno;
import dev.eliascastro.sgb.infra.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

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
    @JoinColumn(name = "isbn")
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf")
    private Aluno aluno;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao ;

    private boolean ativo;

    private Double multa;


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
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(7);
        this.multa = 0.0;
        this.ativo = true;
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
