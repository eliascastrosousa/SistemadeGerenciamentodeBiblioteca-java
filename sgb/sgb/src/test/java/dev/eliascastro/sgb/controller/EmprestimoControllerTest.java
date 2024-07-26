//package dev.eliascastro.sgb.controller;
//
//import dev.eliascastro.sgb.application.emprestimo.DadosCadastroEmprestimoLivro;
//import dev.eliascastro.sgb.application.emprestimo.DadosDetalhamentoEmprestimo;
//import dev.eliascastro.sgb.infra.aluno.Aluno;
//import dev.eliascastro.sgb.application.aluno.DadosCadastroAluno;
//import dev.eliascastro.sgb.infra.emprestimo.EmprestimoRepository;
//import dev.eliascastro.sgb.infra.endereco.DadosEndereco;
//import dev.eliascastro.sgb.application.livro.DadosCadastroLivro;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//class EmprestimoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private JacksonTester<DadosCadastroEmprestimoLivro> dadosCadastroEmprestimoLivroJson;
//    @MockBean
//    private EmprestimoDeLivros emprestimoDeLivros;
//
//    @Autowired
//    private JacksonTester<DadosDetalhamentoEmprestimo> dadosDetalhamentoEmprestimoJson ;
//
//    private LocalDate dataHoje = LocalDate.now();
//    private LocalDate dataSemanaQueVem = dataHoje.plusDays(7);
//
//    @Autowired
//    private EmprestimoRepository emprestimoRepository;
//
//
//    @Test
//    @DisplayName("deveria devolver codigo http 400 quando informações estão invalidas")
//    void cenario01() throws Exception {
//        var response = mockMvc.perform(post("/emprestimos"))
//                .andReturn().getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
//
////    @Test
////    @DisplayName("deveria devolver codigo http 200 quando informações estão validas")
////    void cenario02() throws Exception {
////
////        var dadosDetalhamento = new DadosDetalhamentoEmprestimo(1l,"nome", "titulo", dataHoje, dataSemanaQueVem);
////        when(emprestimoDeLivros.emprestar(any())).thenReturn(dadosDetalhamento);
////        var response = mockMvc.perform(post("/emprestimos")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(dadosCadastroEmprestimoLivroJson.write(
////                    new DadosCadastroEmprestimoLivro(1l,1l))
////                                .getJson()))
////                .andReturn().getResponse();
////
////        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
////
////    }
////
////    @Test
////    @DisplayName("Teste de multa emprestimo de livros atrasado 1 semana")
////    void cenario03()  {
////        Aluno aluno = new Aluno(dadosCadastroAluno01());
////        Livro livro = new Livro(dadosCadastroLivro01());
////        LocalDate d01 = LocalDate.parse("2024-05-08");
////        LocalDate d02 = LocalDate.parse("2024-05-15");
////        Emprestimo emprestimo = new Emprestimo(livro, aluno, d01, d02);
////        emprestimo.verificaMulta();
////        double multa = emprestimo.getMulta();
////        assertEquals(emprestimo.getMulta(), 3.5);
////    }
//
//    @Test
//    @DisplayName("Teste limite de emprestimo")
//    void cenario04()  {
//        Aluno aluno = new Aluno(dadosCadastroAluno01());
//        aluno.decrescentaAoLimiteEmprestimo();
//        assertEquals(aluno.getLimiteLivros(), 2);
//    }
//
//
//    private DadosCadastroAluno dadosCadastroAluno01(){
//        return new DadosCadastroAluno(
//                "aluno",
//                "aluno@email.com",
//                "00000000001",
//                "00000000001",
//                dadosEndereco()
//        );
//    }
//
//    private DadosCadastroAluno dadosCadastroAluno02(){
//        return new DadosCadastroAluno(
//                "aluno",
//                "aluno@email.com",
//                "00000000001",
//                "00000000001",
//                dadosEndereco()
//        );
//    }
//
//    private DadosEndereco dadosEndereco() {
//        return new DadosEndereco(
//                "rua xpto",
//                "bairro",
//                "00000000",
//                "Brasilia",
//                "DF",
//                null,
//                null
//        );
//    }
//
//    private DadosCadastroLivro dadosCadastroLivro01(){
//        return new DadosCadastroLivro(
//                "teste",
//                "elias",
//                "00000000004",
//                "teste"
//        );
//    }
//
//    private DadosCadastroLivro dadosCadastroLivro02(){
//        return new DadosCadastroLivro(
//                "teste",
//                "elias",
//                "00000000004",
//                "teste"
//        );
//    }
//
//    private DadosCadastroLivro dadosCadastroLivro03(){
//        return new DadosCadastroLivro(
//                "teste",
//                "elias",
//                "00000000004",
//                "teste"
//        );
//    }
//    private DadosCadastroLivro dadosCadastroLivro04(){
//        return new DadosCadastroLivro(
//                "teste",
//                "elias",
//                "00000000004",
//                "teste"
//        );
//    }
//}