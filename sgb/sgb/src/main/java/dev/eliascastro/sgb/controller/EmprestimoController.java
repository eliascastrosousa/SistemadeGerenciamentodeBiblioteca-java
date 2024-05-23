package dev.eliascastro.sgb.controller;


import dev.eliascastro.sgb.model.aluno.DadosDetalhamentoAluno;
import dev.eliascastro.sgb.model.emprestimo.*;
import dev.eliascastro.sgb.model.livro.DadosDetalhamentoLivro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoDeLivros emprestimoDeLivros;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarEmprestimo(@RequestBody @Valid DadosCadastroEmprestimoLivro dados, UriComponentsBuilder uriBuilder ){
        var data = emprestimoDeLivros.emprestar(dados);
        var emprestimo = emprestimoRepository.getReferenceById(data.id());
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEmprestimo(emprestimo));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoEmprestimo>> listarEmprestimos(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        var page = emprestimoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoEmprestimo::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhes(@PathVariable Long id){
        var emprestimo = emprestimoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEmprestimo(emprestimo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity devolucao(@PathVariable Long id){
        emprestimoDeLivros.devolucao(id);
        var emprestimo = emprestimoRepository.getReferenceById(id);
        var multa = emprestimo.getMulta();
        if (multa > 0.0){
            return ResponseEntity.ok("Devolucao realizada com sucesso! Atraso gerou multa de R$"
                    + multa + " adicionado ao saldo do Aluno na biblioteca. " ) ;
        }
        return ResponseEntity.noContent().build();
    }

}
