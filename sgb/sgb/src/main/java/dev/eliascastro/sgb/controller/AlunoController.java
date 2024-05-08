package dev.eliascastro.sgb.controller;

import dev.eliascastro.sgb.model.aluno.*;
import dev.eliascastro.sgb.model.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroAluno dados){
        var aluno = new Aluno(dados);
        alunoRepository.save(aluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listar(Pageable paginacao) {
        var page = alunoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoAluno::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoAluno dados){
        var aluno = alunoRepository.getReferenceById(dados.id());
        aluno.atualizarAluno(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamento(@PathVariable Long id) {
        var aluno = alunoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.deletar();
        return ResponseEntity.noContent().build();
    }
}