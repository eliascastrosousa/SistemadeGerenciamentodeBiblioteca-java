package dev.eliascastro.sgb.application.livro;

import dev.eliascastro.sgb.application.aluno.DadosAtualizacaoAluno;
import dev.eliascastro.sgb.application.aluno.DadosCadastroAluno;
import dev.eliascastro.sgb.infra.aluno.Aluno;
import dev.eliascastro.sgb.infra.endereco.DadosEndereco;
import dev.eliascastro.sgb.infra.endereco.Endereco;
import dev.eliascastro.sgb.infra.livro.Livro;
import dev.eliascastro.sgb.infra.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public DadosCadastroLivro cadastrarLivro(DadosCadastroLivro dados) {
        var livro = new Livro(dados);
        repository.save(livro);
        return converteDados(livro);
    }

    public List<DadosCadastroLivro> listarTodos(Pageable paginacao) {
        Page<Livro> livros = repository.findAllByAtivoTrueAndDisponivelTrue(paginacao);
        return livros.stream()
                .map(this::converteDados)
                .collect(Collectors.toList());
    }

    public Livro atualizarLivro(DadosAtualizacaoLivro dados, String isbn) {
        var livro = repository.findByIsbn(isbn);

        if (dados.autor() != null) {
            livro.setAutor(dados.autor());
        }
        if (dados.titulo() != null) {
            livro.setTitulo(dados.titulo());
        }
        if (dados.categoriaLivro() != null) {
            livro.setCategoriaLivro(dados.categoriaLivro());
        }
        repository.save(livro);
        return livro;
    }

    public Livro detalharLivro(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public void deletarLivro(String isbn) {
        var livro = repository.findByIsbn(isbn);
        livro.setAtivo(false);
    }

    private DadosCadastroLivro converteDados(Livro livro) {
        return new DadosCadastroLivro(
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getCategoriaLivro()
        );
    }


}
