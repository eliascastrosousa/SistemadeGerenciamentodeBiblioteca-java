package dev.eliascastro.sgb.controller;

import dev.eliascastro.sgb.model.aluno.*;
import dev.eliascastro.sgb.model.endereco.DadosEndereco;
import dev.eliascastro.sgb.model.livro.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.web.servlet.function.RequestPredicates.GET;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class LivroControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosCadastroLivro> dadosCadastroLivroJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoLivro> dadosDetalhamentoLivroJacksonTester;

    @Autowired
    private JacksonTester<DadosAtualizacaoLivro> dadosAtualizacaoLivroJacksonTester;
    @MockBean
    private LivroRepository livroRepository;

    @Test
    @DisplayName("(POST) Devera retornar codigo http 400 quando informacoes estao invalidas")
    void cenario01() throws Exception {
        var response = mockMvc.perform(post("/livros"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("(POST) Devera retornar codigo http 200 quando informacoes estao validas")
    void cenario02() throws Exception {

        when(livroRepository.save(any())).thenReturn(new Livro(dadosCadastroLivro()));

        var response = mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroLivroJacksonTester.write(dadosCadastroLivro()).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoLivroJacksonTester.write(dadosDetalhamentoLivro()).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("(GET) Deve retornar codigo http 200 quando informacoes estao validas")
    void cenario03() throws Exception {
        when(livroRepository.save(any())).thenReturn(new Livro(dadosCadastroLivro()));
        Livro livro = new Livro(dadosCadastroLivro());
        when(livroRepository.findAllByDisponivelTrue(any())).thenReturn(Page.empty());

        var response = mockMvc.perform(get("/livros"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("(GET detalhes) Deve retornar codigo http 200 quando informacoes estao validas")
    void cenario04() throws Exception {
        when(livroRepository.save(any())).thenReturn(new Livro(dadosCadastroLivro()));
        Livro livro = new Livro(dadosCadastroLivro());
        when(livroRepository.getReferenceById(any())).thenReturn(livro);

        var response = mockMvc.perform(get("/livros/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("(GET) Devera retornar codigo http 404 quando nao for enviado o id do livro")
    void cenario05() throws Exception {
        when(livroRepository.save(any())).thenReturn(new Livro(dadosCadastroLivro()));
        Livro livro = new Livro(dadosCadastroLivro());
        when(livroRepository.getReferenceById(any())).thenReturn(livro);

        var response = mockMvc.perform(get("/livros/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("(PUT) Deveria retornar codigo http 200 quando id nao encontrado")
    void cenario06() throws Exception {
        Livro livro = new Livro(dadosCadastroLivro());

        when(livroRepository.getReferenceById(any())).thenReturn(livro);

        var response = mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoLivroJacksonTester.write(dadosAtualizacaoLivro()).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoLivroJacksonTester.write(dadosDetalhamentoLivro()).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 200 quando id encontrado")
    void cenario07() throws Exception {
        Livro livro = new Livro(dadosCadastroLivro());
        when(livroRepository.getReferenceById(any())).thenReturn(livro);

        var response = mockMvc.perform(delete("/livros/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 404 quando id nao for passado")
    void cenario08() throws Exception {
        Livro livro = new Livro(dadosCadastroLivro());
        when(livroRepository.getReferenceById(any())).thenReturn(livro);

        var response = mockMvc.perform(delete("/livros/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
    @Test
    @DisplayName("(DELETE) Deve retornar codigo http 404 quando id nao for encontrado")
    void cenario09() throws Exception {
        when(livroRepository.getReferenceById(any())).thenReturn(null);
        var response = mockMvc.perform(delete("/alunos/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    private DadosCadastroLivro dadosCadastroLivro(){
        return new DadosCadastroLivro(
                "teste",
                "elias",
                "00000000004",
                "teste"
        );
    }
    private DadosDetalhamentoLivro dadosDetalhamentoLivro(){
        return new DadosDetalhamentoLivro(
                "teste",
                "elias",
                "teste");
    }

    private DadosAtualizacaoLivro dadosAtualizacaoLivro(){
        return new DadosAtualizacaoLivro(
                "teste",
                "elias",
                "teste"
                );
    }





}