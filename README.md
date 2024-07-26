![LinkedIn banner designer marrom estiloso](https://github.com/user-attachments/assets/e2b8f296-570a-4801-8465-a5654496a275)

## Sobre
Este projeto é uma API para o gerenciamento interno de uma biblioteca, incluindo funcionalidades para CRUD de Alunos, Livros e Empréstimos. O desenvolvimento foi realizado utilizando Java 17 e o framework Spring Boot, oferecendo uma solução moderna e escalável para o gerenciamento de bibliotecas.

## Requisitos

Ter o docker instalado no computador, 

---

## Regras de negócio

- **Cadastro de Alunos:**
 Requisitos para registro de alunos como obrigatoriedade de nome completo, email e telefone.
- Ao gerar o cadastro do aluno, é incluido um limite de livros que podem ser emprestados.
- Ao atualizar o aluno só podem ser atualizados nome e telefone e endereço.
- Ao deletar o aluno da base de dados, o aluno é arquivado no sistema desativando seu perfil.
  
- **Gestão de Inventário:** Controle de estoque de livros disponíveis.

- **Empréstimos:**
    Regras sobre o tempo de empréstimo de livros e penalidades por atraso como multa.
    Ao emprestar o livro, automaticamente é decescido um limite de emprestimos do aluno e o livro é alterado para indisponivel na base de dados.
  Quando o aluno utiliza todo seu limite de emprestimos, fica impossibilitado de realizar novos emprestimos até a devolução de um item.
  Caso o item seja devolvido depois da data de devolução, é incluido em seu perfil uma multa, que quando chega a um limite de valor, o mesmo fica impedido de realizar novos emprestimos até realizar o pagamento do saldo da conta.

---

## Tecnologias
As tecnologias utilizadas neste projeto incluem:
- **Java 17:** Linguagem de programação usada para o desenvolvimento da API.
- **Spring Boot:** Framework utilizado para construir a API REST.
- **Spring Security:** Framework utilizado para controle de acesso e autenticação do usuário, utilizando o protocolo de comunicação STATELESS.
- **Spring Data JPA e Hibernate:** Ferramentas utilizadas para simplificar o acesso e manipulação de dados no banco de dados MySQL.
- **MySQL:** Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Redis:** Sistema de cache utilizado para melhorar o desempenho e a escalabilidade da aplicação.
- **Proxy Nginx:** Servidor proxy reverso utilizado para gerenciar o tráfego e aumentar a segurança e desempenho da aplicação.
- **Swagger:** Ferramenta utilizada para documentação e testes interativos da API.
- **Docker:** Utilizado para conteinerizar a aplicação, facilitando o desenvolvimento, teste e implantação.
- **Docker Compose:** Ferramenta utilizada para definir e gerenciar multi-containers Docker, simplificando a orquestração de diferentes serviços.
- **Amazon EC2:** Plataforma de nuvem usada para hospedar a aplicação.
- **Postman:** Ferramenta utilizada para testar e desenvolver APIs, permitindo a validação dos endpoints da API de forma rápida e eficiente.

---

## Padrões de projeto aplicados
Nesta seção, você pode descrever os padrões de design utilizados, como:
- **MVC (Model-View-Controller):** Estrutura utilizada para separar a lógica de negócios, a lógica de apresentação e os dados.
- **DAO (Data Access Object):** Padrão utilizado para abstrair e encapsular todos os acessos ao banco de dados.

## Arquitetura
A arquitetura do projeto é baseada em microservices ou monolítica, dependendo da abordagem escolhida. Descreva como os componentes da aplicação estão estruturados e como eles interagem entre si.

## Paradigmas
Os paradigmas de programação adotados incluem:
- **Programação Orientada a Objetos (POO):** Utilizada para organizar o código em torno de objetos e classes.
- **Desenvolvimento Ágil:** Abordagem utilizada para o desenvolvimento iterativo e incremental do software.

## Endpoints
Detalhe os endpoints disponíveis na API, incluindo os métodos HTTP e as rotas associadas, por exemplo:
- **GET /alunos:** Retorna a lista de alunos.
- **POST /alunos:** Cria um novo aluno.
- **GET /livros:** Retorna a lista de livros.
- **POST /livros:** Adiciona um novo livro.

## Cobertura de testes
Descreva a cobertura de testes da aplicação, incluindo testes unitários, de integração e end-to-end. Explique como os testes foram realizados e o nível de cobertura alcançado.

---

Essa estrutura proporciona uma visão clara e organizada do seu projeto, facilitando a compreensão para outros desenvolvedores e colaboradores.
