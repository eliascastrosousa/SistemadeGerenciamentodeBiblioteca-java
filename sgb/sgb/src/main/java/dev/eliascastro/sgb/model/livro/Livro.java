package dev.eliascastro.sgb.model.livro;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livros")
@Entity(name = "Livro")
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private boolean disponivel;

    public Livro(DadosCadastroLivro dados){
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.isbn = dados.isbn();
        this.genero = dados.genero();
        this.disponivel = true;
    }


    public void atualizar(DadosAtualizacaoLivro dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
        if (dados.isbn() != null){
            this.isbn = dados.isbn();
        }
        if (dados.genero() != null){
            this.genero = dados.genero();
        }
    }

    public void marcarEmprestimo(){
        disponivel = false;
    }



}
