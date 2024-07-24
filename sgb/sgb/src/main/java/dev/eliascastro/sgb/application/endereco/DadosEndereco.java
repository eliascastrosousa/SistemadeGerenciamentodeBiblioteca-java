package dev.eliascastro.sgb.application.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.dominio.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEndereco(

        @NotBlank
        String cep,

        @NotBlank
        String logradouro,

        String numero,

        String complemento

        ) {

        public DadosEndereco(Endereco endereco){
                this(endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento());
        }
}
