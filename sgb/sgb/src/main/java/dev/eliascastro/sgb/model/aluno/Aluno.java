package dev.eliascastro.sgb.model.aluno;

import dev.eliascastro.sgb.model.endereco.Endereco;
import dev.eliascastro.sgb.model.usuario.DadosAtualizacaoUsuario;
import dev.eliascastro.sgb.model.usuario.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alunos")
@Entity(name = "Aluno")
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private boolean ativo;
    private Integer limiteLivros;
    private Double multa;

    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
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
            acrescentaAoLimiteEmprestimo();
        }
    }

    public void desativarAluno() {
        this.ativo = false;
    }


    public void decrescentaAoLimiteEmprestimo() {
        this.limiteLivros--;
    }
    public void acrescentaAoLimiteEmprestimo() {
        this.limiteLivros++;
    }

}
