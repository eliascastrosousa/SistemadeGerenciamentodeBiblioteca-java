package dev.eliascastro.sgb.application.usuario;

import dev.eliascastro.sgb.infra.usuario.Usuario;
import dev.eliascastro.sgb.infra.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroUsuario dados){
        var usuario = usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }



    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao ) {
        var page = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoUsuario dados){
      var usuario = usuarioRepository.getReferenceById(dados.id());
      usuario.atualizarUsuario(dados);
      return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamento(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        System.out.println(usuario.isAtivo());
        System.out.println(usuario.getNome());
        usuario.deletar();
        System.out.println(usuario.isAtivo());
        return ResponseEntity.noContent().build();
    }
}

