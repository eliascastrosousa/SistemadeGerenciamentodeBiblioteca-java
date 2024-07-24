package dev.eliascastro.sgb.infra.usuario;

import dev.eliascastro.sgb.infra.endereco.Endereco;
import dev.eliascastro.sgb.application.usuario.DadosAtualizacaoUsuario;
import dev.eliascastro.sgb.application.usuario.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private boolean ativo;


    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.login = dados.login();
        this.senha = dados.senha();
        this.ativo = true;
    }

    public Usuario(String nome, String login, String senhaCriptografada) {
        this.nome = nome;
        this.login = login;
        this.senha = senhaCriptografada;
        this.ativo = true;
    }

    public void atualizarUsuario(DadosAtualizacaoUsuario dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.login() != null) {
            this.login = dados.login();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }

    }

    public void deletar() {
        ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
