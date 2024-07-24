package dev.eliascastro.sgb.dominio.usuario;

public class Usuario {
    private String nome;
    private String login;
    private String senha;


    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario() {
    }

    public Usuario(Long id, String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public static class Builder{
        private Usuario usuario;

        public Builder(){
            usuario = new Usuario();
        }

        public Builder comNome(String nome){
            usuario.nome = nome;
            return this;
        }

        public Builder comLogin(String login){
            usuario.login = login;
            return this;
        }

        public Builder comSenha(String senha){
            usuario.senha = senha;
            return this;
        }
        public Usuario Build(){
            return usuario;
        }
    }

    /*
    * public static class Builder{
        private Livro livro;

        public Builder() {
            livro = new Livro();
        }

        public Builder comTitulo(String titulo){
            livro.titulo = titulo;
            return this;
        }*/

    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
