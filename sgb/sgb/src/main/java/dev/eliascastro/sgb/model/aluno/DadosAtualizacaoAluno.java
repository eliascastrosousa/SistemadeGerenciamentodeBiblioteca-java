package dev.eliascastro.sgb.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtualizacaoAluno(
        @NotBlank
        Long id,

        String nome,

        @Pattern(regexp = "\\d{11}")
        String telefone,

        @Valid
        DadosEndereco endereco) {


}
