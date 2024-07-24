package dev.eliascastro.sgb.application.usuario;

import dev.eliascastro.sgb.infra.usuario.Usuario;
import dev.eliascastro.sgb.infra.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(DadosCadastroUsuario dados) {
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados.nome(),dados.login(), senhaCriptografada);
        usuarioRepository.save(usuario);
        return usuario;
    }
}