![LinkedIn banner designer marrom estiloso](https://github.com/user-attachments/assets/e2b8f296-570a-4801-8465-a5654496a275)

## Sobre
Este projeto é uma API para o gerenciamento interno de uma biblioteca, incluindo funcionalidades para CRUD de Alunos, Livros e Empréstimos. O desenvolvimento foi realizado utilizando Java 17 e o framework Spring Boot, oferecendo uma solução moderna e escalável para o gerenciamento de bibliotecas.

## Requisitos

Para rodar o projeto com container Docker
* Ter o docker e docker-compose instalado no computador;
* Ferramenta de testes de API. ex: Insomnia e Postman;

```
docker compose up
```

Para rodar direto na maquina

* Ter o Java17 instalado na maquina
* IDE para compilar e executar o projeto.
* Ter o MySQL instalado
* Ferramenta de testes de API. ex: Insomnia e Postman;

Instalar todas as dependencias do Maven

![image](https://github.com/user-attachments/assets/230a8b17-7166-4b91-96e1-ba88ee574eba)

Executar aplicação

![image](https://github.com/user-attachments/assets/493b44e7-70e4-4cdc-ade7-45db765049c3)



## Regras de negócio

**Alunos:**
- Requisitos para registro de alunos como obrigatoriedade de nome completo, email e telefone.
- Ao gerar o cadastro do aluno, é incluido um limite de livros que podem ser emprestados.
- Ao atualizar o aluno só podem ser atualizados nome e telefone e endereço.
- Ao deletar o aluno da base de dados, o aluno é arquivado no sistema desativando seu perfil.
  
**Empréstimos:**
- Regras sobre o tempo de empréstimo de livros e penalidades por atraso como multa.
- Ao emprestar o livro, automaticamente é decrescido um limite de emprestimos do aluno e o livro é alterado para indisponivel na base de dados.
- Quando o aluno utiliza todo seu limite de emprestimos, fica impossibilitado de realizar novos emprestimos até a devolução de um item.
- Caso o item seja devolvido depois da data de devolução, é incluido em seu perfil uma multa, que quando chega a um limite de valor, o mesmo fica impedido de realizar novos emprestimos até realizar o pagamento do saldo da conta.

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

## Paradigmas
Os paradigmas de programação adotados incluem:
- **Programação Orientada a Objetos (POO):** Utilizada para organizar o código em torno de objetos e classes.
- **Desenvolvimento Ágil:** Abordagem utilizada para o desenvolvimento iterativo e incremental do software.

## Endpoints

Para utilizar o Swagger UI no servidor local, acesse o link:
```
  localhost:8080/swagger-ui/index.html
```

Livro

- **GET /livros:** Retorna a lista de livros.
- **GET /livros/{isbn}:** Retorna livro especifico.
- **POST /livros:** Cria um novo livro.
- **PUT /livros/{isbn}:** Atualiza um livro.
- **DELETE /livros/{isbn}:** Deleta(inativa) um livro especifico.

Aluno

- **GET /alunos:** Retorna a lista de alunos.
- **GET /alunos/{cpf}:** Retorna aluno especifico.
- **POST /alunos:** Cria um novo aluno.
- **PUT /alunos/{cpf}:** Atualiza um aluno.
- **DELETE /alunos/{cpf}:** Deleta(inativa) um aluno especifico.

Emprestimos

- **GET /emprestimos:** Retorna a lista de emprestimos.
- **GET /emprestimos/{id}:** Retorna emprestimo especifico.
- **POST /emprestimos:** Cria um novo emprestimo.
- **PUT /emprestimos/{id}:** Atualiza um emprestimo.
- **DELETE /emprestimos/{id}:** Deleta(inativa) um emprestimo especifico.

Login e Usuarios

- **POST /login:** Verifica credenciais e Retorna o Token de autenticação.
- **GET /usuarios:** Retorna lista de usuarios.
- **GET /usuarios/{id}:** Retorna usuario especifico.
- **POST /usuarios:** Cria um novo usuario.
- **PUT /usuarios/{id}:** Atualiza um usuario.
- **DELETE /usuarios/{id}:** Deleta(inativa) um usuario especifico.
