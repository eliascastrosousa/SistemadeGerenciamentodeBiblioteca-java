package dev.eliascastro.sgb.model.usuario;

import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import dev.eliascastro.sgb.model.endereco.Endereco;

public record DadosUsuario(String nome,
                           String email,
                           String telefone,
                           String cpf,
                           Endereco endereco
                           ) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(),
                usuario.getCpf(), usuario.getEndereco());
    }
}
