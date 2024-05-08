package dev.eliascastro.sgb.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import dev.eliascastro.sgb.model.endereco.Endereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtualizacaoUsuario(Long id,
                                      String nome,
                                      String email,
                                      String cpf,
                                      String telefone,

                                      DadosEndereco endereco) {

}
