package dev.eliascastro.sgb.dominio.livro;


public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private CategoriaLivro categoriaLivro;
    private boolean disponivel;

    public Livro() {
    }

    public Livro(String titulo, String autor, String isbn, CategoriaLivro categoriaLivro, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoriaLivro = categoriaLivro;
        this.disponivel = disponivel;
    }

    public static class Builder{
        private Livro livro;

        public Builder() {
            livro = new Livro();
        }

        public Builder comTitulo(String titulo){
            livro.titulo = titulo;
            return this;
        }

        public Builder comIsbn(String isbn){
            livro.isbn = isbn;
            return this;
        }

        public Builder comAutor(String autor){
            livro.autor = autor;
            return this;
        }

        public Builder comCategoriaLivro(CategoriaLivro categoriaLivro){
            livro.categoriaLivro = categoriaLivro;
            return this;
        }

        public Builder comDisponibilidade(Boolean disponivel){
            livro.disponivel = disponivel;
            return this;
        }

        public Livro Build(){
            return livro;
        }
    }

    public CategoriaLivro getCategoriaLivro() {
        return categoriaLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }


    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoriaLivro=" + categoriaLivro +
                ", disponivel=" + disponivel +
                '}';
    }
}
