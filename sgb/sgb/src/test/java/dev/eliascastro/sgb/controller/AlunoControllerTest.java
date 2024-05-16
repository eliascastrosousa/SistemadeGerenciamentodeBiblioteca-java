package dev.eliascastro.sgb.controller;

import dev.eliascastro.sgb.model.aluno.*;
import dev.eliascastro.sgb.model.emprestimo.DadosCadastroEmprestimoLivro;
import dev.eliascastro.sgb.model.emprestimo.DadosDetalhamentoEmprestimo;
import dev.eliascastro.sgb.model.emprestimo.EmprestimoDeLivros;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import dev.eliascastro.sgb.model.livro.Livro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosCadastroAluno> dadosCadastroAlunoJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoAluno> dadosDetalhamentoAlunoJson;

    @Autowired
    private JacksonTester<DadosAtualizacaoAluno> dadosAtualizacaoAlunoJson;
    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("(POST) Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void cenario01() throws Exception {
        var response = mockMvc.perform(post("/alunos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("(POST) Deveria devolver codigo http 201 quando informacoes estao validas")
    void cenario02() throws Exception {

        when(alunoRepository.save(any())).thenReturn(new Aluno(dadosCadastroAluno()));

        var response = mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroAlunoJson.write(dadosCadastroAluno()).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoAlunoJson.write(dadosDetalhamentoAluno()).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("(GET) Deveria retornar codigo http 200 quando informacoes estao validas")
    void cenario03() throws Exception {
        when(alunoRepository.save(any())).thenReturn(new Aluno(dadosCadastroAluno()));
        when(alunoRepository.findAllByAtivoTrue(any())).thenReturn(Page.empty());

        var response = mockMvc.perform(get("/alunos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("(GET) Deveria retornar codigo http 200 quando informacoes estao validas")
    void cenario04() throws Exception {
        when(alunoRepository.save(any())).thenReturn(new Aluno(dadosCadastroAluno()));
        Aluno aluno = new Aluno(dadosCadastroAluno());
        when(alunoRepository.getReferenceById(any())).thenReturn(aluno);

        var response = mockMvc.perform(get("/alunos/1"))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoAlunoJson.write(dadosDetalhamentoAluno()).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    @Test
    @DisplayName("(GET) Deveria retornar codigo http 404 quando id nao encontrado")
    void cenario05() throws Exception {
        Aluno aluno = new Aluno(dadosCadastroAluno());
        when(alunoRepository.getReferenceById(any())).thenReturn(aluno);

        var response = mockMvc.perform(get("/alunos/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    @DisplayName("(PUT) Deveria retornar codigo http 200 quando id nao encontrado")
    void cenario06() throws Exception {
        Aluno aluno = new Aluno(dadosCadastroAluno());
        when(alunoRepository.getReferenceById(any())).thenReturn(aluno);

        var response = mockMvc.perform(put("/alunos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoAlunoJson.write(dadosAtualizacaoAluno()).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoAlunoJson.write(dadosDetalhamentoAluno()).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 200 quando id encontrado")
    void cenario07() throws Exception {
        Aluno aluno = new Aluno(dadosCadastroAluno());
        when(alunoRepository.getReferenceById(any())).thenReturn(aluno);

        var response = mockMvc.perform(delete("/alunos/1"))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());

    }

    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 404 quando id nao for passado")
    void cenario08() throws Exception {
        Aluno aluno = new Aluno(dadosCadastroAluno());
        when(alunoRepository.getReferenceById(any())).thenReturn(aluno);

        var response = mockMvc.perform(delete("/alunos/"))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 500 quando id nao for encontrado")
    void cenario09() throws Exception {
        when(alunoRepository.getReferenceById(any())).thenReturn(null);
        var response = mockMvc.perform(delete("/alunos/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }




    private DadosCadastroAluno dadosCadastroAluno(){
        return new DadosCadastroAluno(
                "aluno",
                "aluno@email.com",
                "00000000001",
                "00000000001",
                dadosEndereco()
        );
    }
    private DadosAtualizacaoAluno dadosAtualizacaoAluno(){
        return new DadosAtualizacaoAluno(
                "aluno@email.com",
                "11984370050",
                dadosEndereco()

        );
    }
    private DadosDetalhamentoAluno dadosDetalhamentoAluno(){
        return new DadosDetalhamentoAluno("aluno",
                "aluno@email.com",
                "00000000001");
    }
    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }


}