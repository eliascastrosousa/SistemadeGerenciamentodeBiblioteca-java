package dev.eliascastro.sgb.application.livro;

import dev.eliascastro.sgb.infra.livro.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder){
        var livro = livroService.cadastrarLivro(dados);
        System.out.println(livro.isbn());
        var uri = uriBuilder.path("/livros/{isbn}").buildAndExpand(livro.isbn()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroLivro>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao ) {
        var page = livroService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity detalhamento(@PathVariable String isbn){
        var livro = livroService.detalharLivro(isbn);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @PutMapping("/{isbn}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable String isbn, @RequestBody @Valid DadosAtualizacaoLivro dados){
        var livro = livroService.atualizarLivro(dados, isbn);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @DeleteMapping("/{isbn}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String isbn){
        livroService.deletarLivro(isbn);
        return ResponseEntity.noContent().build();
    }
}
