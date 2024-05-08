package dev.eliascastro.sgb.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroUsuario(
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco) {
}
