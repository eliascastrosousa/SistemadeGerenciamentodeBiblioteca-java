package dev.eliascastro.sgb.infra.aluno;

import dev.eliascastro.sgb.application.aluno.DadosAtualizacaoAluno;
import dev.eliascastro.sgb.application.aluno.DadosCadastroAluno;
import dev.eliascastro.sgb.infra.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alunos")
@Entity(name = "Aluno")
public class Aluno {
    private String nome;
    @Id
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private boolean ativo;
    private Integer limiteLivros;
    private Double multa;


    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();

        this.endereco = new Endereco(dados.endereco());

        this.ativo = true;
        this.limiteLivros = 3;
        this.multa = 0.0;
    }

    public void atualizarAluno(DadosAtualizacaoAluno dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizar(dados.endereco());
        }
    }

    public void adicionarMulta(double multa){
        this.multa += multa;
    }

    public void zerarMulta(double pagamento){
        this.multa -= pagamento;
        if (multa == 0.0){
            acrescentarLimiteDeEmprestimo();
        }
    }

    public void desativarAluno() {
        this.ativo = false;
    }


    public void decrescentarLimiteDeEmprestimo() {
        this.limiteLivros--;
    }
    public void acrescentarLimiteDeEmprestimo() {
        this.limiteLivros++;
    }

}
