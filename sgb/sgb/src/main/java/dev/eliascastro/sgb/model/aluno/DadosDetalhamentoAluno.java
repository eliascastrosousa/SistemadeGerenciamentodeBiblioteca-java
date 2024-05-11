package dev.eliascastro.sgb.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.emprestimo.Emprestimo;
import dev.eliascastro.sgb.model.usuario.Usuario;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoAluno(
                                String nome,
                                String email,
                                String telefone
) {
    public DadosDetalhamentoAluno(Aluno aluno ){
        this(aluno.getNome(), aluno.getEmail(), aluno.getTelefone());
    }


}