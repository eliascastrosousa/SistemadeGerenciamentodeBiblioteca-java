package dev.eliascastro.sgb.application.usuario;

import dev.eliascastro.sgb.infra.endereco.Endereco;
import dev.eliascastro.sgb.infra.usuario.Usuario;

public record DadosUsuario(String nome,
                           String login,
                           String senha
                           ) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }
}
