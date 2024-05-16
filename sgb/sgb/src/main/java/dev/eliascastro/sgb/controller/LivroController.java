package dev.eliascastro.sgb.controller;

import dev.eliascastro.sgb.model.livro.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados){
        var livro = new Livro(dados);
        repository.save(livro);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoLivro>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = repository.findAllByDisponivelTrue(paginacao).map(DadosDetalhamentoLivro::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamento(@PathVariable Long id){
        var livro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoLivro dados){
        var livro = repository.getReferenceById(id);
        livro.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        var livro = repository.getReferenceById(id);
        repository.delete(livro);
        return ResponseEntity.noContent().build();
    }
}
