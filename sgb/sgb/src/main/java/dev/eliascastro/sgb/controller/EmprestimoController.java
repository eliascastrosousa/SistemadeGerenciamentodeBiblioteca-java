package dev.eliascastro.sgb.controller;


import dev.eliascastro.sgb.model.aluno.DadosDetalhamentoAluno;
import dev.eliascastro.sgb.model.emprestimo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoDeLivros emprestimoDeLivros;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarEmprestimo(@RequestBody @Valid DadosCadastroEmprestimoLivro dados ){
        var emprestimo = emprestimoDeLivros.emprestar(dados);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoEmprestimo>> listarEmprestimos(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        var page = emprestimoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoEmprestimo::new);
        return ResponseEntity.ok(page);
    }

//    @GetMapping("/detalhamento")
//    public ResponseEntity<Page<DadosDetalhamentoEmprestimo>> listarEmprestimosComDetalhes(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
//        var page = emprestimoRepository.findAllByAtivoTrueComDetalhes(paginacao).map(DadosDetalhamentoEmprestimo::new);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping("/{id}")
    public ResponseEntity detalhes(@PathVariable Long id){
        var emprestimo = emprestimoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEmprestimo(emprestimo));
        //criar uma validação para apenas mostrar o item se ele estiver ativo,
        // caso contrario devolver uma exception "Dados de emprestimo não disponivel."
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity devolucao(@PathVariable Long id){
        emprestimoDeLivros.devolucao(id);
        return ResponseEntity.noContent().build();
    }

}
