package dev.eliascastro.sgb.application.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.application.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtualizacaoAluno(

        String nome,

        @Pattern(regexp = "\\d{11}")
        String telefone,

        @Valid
        DadosEndereco endereco) {


}
