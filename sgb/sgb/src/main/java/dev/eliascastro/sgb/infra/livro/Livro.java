package dev.eliascastro.sgb.infra.livro;

import dev.eliascastro.sgb.application.livro.DadosAtualizacaoLivro;
import dev.eliascastro.sgb.application.livro.DadosCadastroLivro;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "livros")
@Entity(name = "Livro")
public class Livro {
    @Id
    private String isbn;
    private String titulo;
    private String autor;
    @Enumerated(EnumType.STRING)
    private CategoriaLivro categoriaLivro;
    private boolean disponivel;
    private boolean ativo;

    public Livro(DadosCadastroLivro dados){
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.isbn = dados.isbn();
        this.categoriaLivro = dados.categoriaLivro();
        this.disponivel = true;
        this.ativo = true;
    }

    public void atualizar(DadosAtualizacaoLivro dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
        if (dados.categoriaLivro() != null){
            this.categoriaLivro = dados.categoriaLivro();
        }
    }

    public void marcarEmprestimo(){
        disponivel = false;
    }

    public void desmarcarEmprestimo(){
        disponivel = true;
    }

}
