package dev.eliascastro.sgb.application.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.infra.usuario.Usuario;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoUsuario(String nome,
                                       String login
                                      ) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getLogin());
    }
}
