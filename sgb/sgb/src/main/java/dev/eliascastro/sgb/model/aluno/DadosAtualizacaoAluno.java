package dev.eliascastro.sgb.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAtualizacaoAluno(Long id,
                                      String nome,
                                      String email,
                                      String cpf,
                                      String telefone,

                                      DadosEndereco endereco) {


}
