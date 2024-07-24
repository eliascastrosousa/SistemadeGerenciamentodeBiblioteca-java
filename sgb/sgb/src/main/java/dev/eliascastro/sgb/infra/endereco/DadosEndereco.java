package dev.eliascastro.sgb.infra.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEndereco(

        @NotBlank
        String logradouro,

        @NotBlank
        String cep,
        String numero,

        @NotBlank
        String complemento

        ) {
}
