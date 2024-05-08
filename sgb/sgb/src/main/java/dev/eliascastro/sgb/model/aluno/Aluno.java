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

    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarAluno(DadosAtualizacaoAluno dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.cpf() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizar(dados.endereco());
        }
    }

    public void deletar() {
        ativo = false;
    }
}
