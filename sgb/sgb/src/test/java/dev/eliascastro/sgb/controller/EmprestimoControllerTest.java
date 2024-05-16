package dev.eliascastro.sgb.controller;

import dev.eliascastro.sgb.model.aluno.Aluno;
import dev.eliascastro.sgb.model.aluno.DadosCadastroAluno;
import dev.eliascastro.sgb.model.emprestimo.*;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EmprestimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosCadastroEmprestimoLivro> dadosCadastroEmprestimoLivroJson;
    @MockBean
    private EmprestimoDeLivros emprestimoDeLivros;

    @Autowired
    private JacksonTester<DadosDetalhamentoEmprestimo> dadosDetalhamentoEmprestimoJson ;

    private LocalDate dataHoje = LocalDate.now();
    private LocalDate dataSemanaQueVem = dataHoje.plusDays(7);

    @Autowired
    private EmprestimoRepository emprestimoRepository;


    @Test
    @DisplayName("deveria devolver codigo http 400 quando informações estão invalidas")
    void cenario01() throws Exception {
        var response = mockMvc.perform(post("/emprestimos"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deveria devolver codigo http 200 quando informações estão validas")
    void cenario02() throws Exception {


        var dadosDetalhamento = new DadosDetalhamentoEmprestimo(null,"nome", "titulo", dataHoje, dataSemanaQueVem);
        when(emprestimoDeLivros.emprestar(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc.perform(post("/emprestimos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroEmprestimoLivroJson.write(
                    new DadosCadastroEmprestimoLivro(1l,1l))
                                .getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoEmprestimoJson.write(new DadosDetalhamentoEmprestimo(null,"nome", "titulo", dataHoje, dataSemanaQueVem)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}