package dev.eliascastro.sgb.application.aluno;

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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder){
        var aluno = alunoService.cadastrarAluno(dados);
        var uri = uriBuilder.path("/alunos/{cpf}").buildAndExpand(aluno.cpf()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroAluno>> listar(@PageableDefault(size = 10, sort = {"nome"})  Pageable paginacao) {
        var page = alunoService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable String cpf, @RequestBody @Valid DadosAtualizacaoAluno dados){
        var aluno = alunoService.atualizarAluno(dados, cpf);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity detalhamento(@PathVariable String cpf) {
        var aluno = alunoService.detalharAluno(cpf);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String cpf){
        alunoService.desativarAluno(cpf);
        return ResponseEntity.notFound().build();
    }
}