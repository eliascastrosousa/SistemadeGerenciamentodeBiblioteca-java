package dev.eliascastro.sgb.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhamentoUsuario(String nome,
                                       String email,
                                       String telefone
                                      ) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
    }
}
