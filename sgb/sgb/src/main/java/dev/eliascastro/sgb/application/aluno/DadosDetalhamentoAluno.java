package dev.eliascastro.sgb.application.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.aluno.Aluno;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoAluno(
                                String nome,
                                String cpf,
                                String email,
                                String telefone
) {
    public DadosDetalhamentoAluno(Aluno aluno ){
        this(aluno.getNome(), aluno.getCpf(), aluno.getEmail(), aluno.getTelefone());
    }


}