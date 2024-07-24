package dev.eliascastro.sgb.dominio.emprestimo;

import dev.eliascastro.sgb.infra.aluno.Aluno;
import dev.eliascastro.sgb.infra.livro.Livro;

import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private Aluno aluno;
    private LocalDate dataEmprestimo = LocalDate.now();
    private LocalDate dataDevolucao = LocalDate.now().plusDays(7);
    private boolean ativo = true;
    private Double multa;

    public Emprestimo() {
    }

    public Emprestimo(Livro livro, Aluno aluno, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean ativo, Double multa) {
        this.livro = livro;
        this.aluno = aluno;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.ativo = ativo;
        this.multa = multa;
    }

    public Livro getLivro() {
        return livro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Double getMulta() {
        return multa;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro +
                ", aluno=" + aluno +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", ativo=" + ativo +
                ", multa=" + multa +
                '}';
    }
}
