package dev.eliascastro.sgb.application.emprestimo;


import dev.eliascastro.sgb.infra.emprestimo.Emprestimo;
import dev.eliascastro.sgb.infra.emprestimo.EmprestimoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarEmprestimo(@RequestBody @Valid DadosCadastroEmprestimoLivro dados, UriComponentsBuilder uriBuilder ){
        var emprestimo = emprestimoService.cadastrarEmprestimo(dados);
        var uri = uriBuilder.path("emprestimo/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEmprestimo(emprestimo));
    }


    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoEmprestimo>> listarEmprestimos(@PageableDefault(size = 10, sort = {"dataEmprestimo"}) Pageable paginacao){
        var page = emprestimoService.listarEmprestimos(paginacao);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhesEmprestimo(@PathVariable Long id){
        var emprestimo = emprestimoService.buscarEmprestimo(id);
        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity devolucao(@PathVariable Long id){
        var devolucao = emprestimoService.devolucaoEmprestimo(id);
        return ResponseEntity.ok(devolucao);
    }
}
