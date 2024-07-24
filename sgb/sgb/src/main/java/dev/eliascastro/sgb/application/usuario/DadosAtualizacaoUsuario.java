package dev.eliascastro.sgb.application.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.endereco.DadosEndereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtualizacaoUsuario(Long id,
                                      String nome,
                                      String login,
                                      String senha
                    ) {

}
