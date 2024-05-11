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

    public Emprestimo(DadosCadastroEmprestimoLivro dados) {

    }

    public Emprestimo( Livro livro, Aluno aluno) {
        this.livro = livro;
        this.aluno = aluno;

    }

    public void arquivar() {
        ativo = false;
    }
}
