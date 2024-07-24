package dev.eliascastro.sgb.application.aluno;

import dev.eliascastro.sgb.infra.aluno.Aluno;
import dev.eliascastro.sgb.infra.aluno.AlunoRepository;
import dev.eliascastro.sgb.application.endereco.DadosEndereco;
import dev.eliascastro.sgb.infra.endereco.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public DadosCadastroAluno cadastrarAluno(DadosCadastroAluno dados) {
        var aluno = new Aluno(dados);
        repository.save(aluno);
        return converteDados(aluno);
    }

    public List<DadosCadastroAluno> listarTodos(Pageable paginacao) {
        Page<Aluno> alunos = repository.findAllByAtivoTrue(paginacao);
        return alunos.stream().map(this::converteDados).collect(Collectors.toList());
    }

    public DadosCadastroAluno atualizarAluno(DadosAtualizacaoAluno dados, String cpf) {
        var alunoReference = repository.getReferenceByCpf(cpf);
        if (alunoReference.isPresent()) {
            var aluno = alunoReference.get();
            if (dados.nome() != null) {
                aluno.setNome(dados.nome());
            }
            if (dados.telefone() != null) {
                aluno.setTelefone(dados.telefone());
            }
            if (dados.endereco() != null) {
                aluno.setEndereco(new Endereco(dados.endereco()));
            }
        }

        repository.save(alunoReference.get());
        return converteDados(alunoReference.get());
    }

    public void desativarAluno(String cpf){
        var alunoReference = repository.getReferenceByCpf(cpf);
        alunoReference.ifPresent(aluno -> aluno.setAtivo(false));
        repository.save(alunoReference.get());
    }

    public DadosCadastroAluno detalharAluno(String cpf) {
        var alunoReference = repository.findByCpf(cpf);
        return converteDados(alunoReference);
    }

    private DadosCadastroAluno converteDados(Aluno aluno) {

        return new DadosCadastroAluno(
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone(),

                new DadosEndereco(
                        aluno.getEndereco().getCep(),
                        aluno.getEndereco().getLogradouro(),
                        aluno.getEndereco().getNumero(),
                        aluno.getEndereco().getComplemento()
                )
        );

    }


}
