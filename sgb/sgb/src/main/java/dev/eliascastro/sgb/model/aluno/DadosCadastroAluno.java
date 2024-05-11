package dev.eliascastro.sgb.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroAluno (
        @NotBlank
        String nome,

        @NotNull
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @NotNull
        @Valid
        DadosEndereco endereco) {
        }