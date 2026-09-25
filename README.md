📚 Sistema de Gerenciamento de Biblioteca

<p align="center">
  <strong>API REST para gerenciamento de biblioteca desenvolvida com Java e Spring Boot.</strong>
</p><p align="center">
  Gerenciamento de livros, alunos, empréstimos e usuários, com autenticação, regras de negócio, persistência em MySQL, cache com Redis e documentação através do Swagger.
</p>---

📌 Sobre o projeto

O Sistema de Gerenciamento de Biblioteca é uma API REST desenvolvida em Java 17 e Spring Boot, criada para centralizar o gerenciamento de uma biblioteca.

A aplicação permite administrar:

- 📚 Livros
- 👨‍🎓 Alunos
- 🔄 Empréstimos
- 👤 Usuários
- 🔐 Autenticação e autorização

Além das operações de CRUD, o sistema possui regras de negócio relacionadas a limite de empréstimos, disponibilidade de livros, devoluções, multas e desativação de registros.

O projeto também foi utilizado como oportunidade para colocar em prática conceitos de arquitetura backend, segurança, persistência de dados, cache, containerização e deploy em nuvem.

---

🎯 Objetivos

O projeto foi desenvolvido com foco no aprendizado e aplicação prática de conceitos importantes do desenvolvimento backend com Java:

- Desenvolvimento de APIs REST;
- Programação Orientada a Objetos;
- Spring Boot;
- Spring Security;
- Autenticação utilizando JWT;
- Spring Data JPA;
- Hibernate;
- Banco de dados relacional;
- Cache com Redis;
- Documentação de APIs;
- Docker e Docker Compose;
- Proxy reverso com Nginx;
- Deploy em ambiente AWS;
- Desenvolvimento e testes de endpoints.

---

🛠️ Tecnologias

<p align="center"><img src="https://img.shields.io/badge/Java%2017-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/><img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/><img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/><img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"/><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/><img src="https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"/><img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/></p>Stack principal

Tecnologia| Utilização
Java 17| Desenvolvimento da aplicação
Spring Boot| Construção da API REST
Spring Security| Autenticação e segurança
Spring Data JPA| Persistência e acesso aos dados
Hibernate| ORM
MySQL| Banco de dados relacional
Redis| Cache
Swagger/OpenAPI| Documentação e testes da API
Docker| Containerização
Docker Compose| Orquestração dos containers
Nginx| Proxy reverso
Amazon EC2| Hospedagem da aplicação
Postman| Testes e desenvolvimento da API

As tecnologias acima estão descritas no próprio repositório do projeto.

---

🏗️ Arquitetura

A aplicação foi desenvolvida seguindo uma organização em camadas, buscando separar as responsabilidades da aplicação.

                    ┌──────────────────────┐
                    │       Cliente        │
                    │ Postman / Front-end  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │       Nginx          │
                    │   Proxy Reverso      │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    Spring Boot       │
                    │                      │
                    │     Controllers      │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Services        │
                    │   Regras de negócio  │
                    └──────────┬───────────┘
                               │
                    ┌──────────┴───────────┐
                    │                      │
                    ▼                      ▼
             ┌─────────────┐       ┌─────────────┐
             │    Redis    │       │    JPA /    │
             │    Cache    │       │  Hibernate  │
             └─────────────┘       └──────┬──────┘
                                          │
                                          ▼
                                   ┌─────────────┐
                                   │    MySQL    │
                                   └─────────────┘

---

🔐 Segurança

A API utiliza Spring Security para controle de acesso e autenticação.

O fluxo de autenticação utiliza uma abordagem stateless, na qual o cliente recebe um token após realizar o login e utiliza esse token para acessar recursos protegidos.

Login

POST /login

O endpoint verifica as credenciais do usuário e retorna o token de autenticação.

---

📚 Funcionalidades

📖 Livros

A API permite realizar operações de gerenciamento dos livros:

GET    /livros
GET    /livros/{isbn}
POST   /livros
PUT    /livros/{isbn}
DELETE /livros/{isbn}

O "DELETE" realiza a desativação do registro em vez de simplesmente remover o livro da base.

---

👨‍🎓 Alunos

Gerenciamento dos alunos cadastrados:

GET    /alunos
GET    /alunos/{cpf}
POST   /alunos
PUT    /alunos/{cpf}
DELETE /alunos/{cpf}

Regras de negócio

No cadastro do aluno são exigidos dados como:

- Nome completo;
- E-mail;
- Telefone.

Também é definido um limite de empréstimos para o aluno.

Na atualização, determinados dados cadastrais podem ser alterados, enquanto a exclusão é tratada como desativação do perfil.

---

🔄 Empréstimos

Gerenciamento do ciclo de empréstimos:

GET    /emprestimos
GET    /emprestimos/{id}
POST   /emprestimos
PUT    /emprestimos/{id}
DELETE /emprestimos/{id}

Durante um empréstimo:

1. O limite de empréstimos do aluno é atualizado;
2. O livro passa a ficar indisponível;
3. A data de devolução é controlada;
4. A devolução atualiza a disponibilidade do livro;
5. Em caso de atraso, pode ser registrada uma multa.

Quando o aluno atinge seu limite de empréstimos, novos empréstimos ficam bloqueados até que um item seja devolvido.

Também existe uma regra relacionada ao acúmulo de multas: quando o saldo atinge determinado limite, novos empréstimos ficam bloqueados até a regularização.

---

👤 Usuários

A API também possui gerenciamento de usuários:

GET    /usuarios
GET    /usuarios/{id}
POST   /usuarios
PUT    /usuarios/{id}
DELETE /usuarios/{id}

O cadastro e a autenticação dos usuários estão integrados ao mecanismo de segurança da aplicação.

---

📖 Documentação da API

O projeto utiliza Swagger/OpenAPI para documentação e testes interativos dos endpoints.

Após iniciar a aplicação localmente, acesse:

http://localhost:8080/swagger-ui/index.html

A interface permite visualizar os endpoints disponíveis, parâmetros, requisições e respostas da API.

---

🐳 Executando com Docker

Uma das formas de executar o projeto é utilizando Docker Compose.

Pré-requisitos

- Docker
- Docker Compose
- Postman ou outra ferramenta para testes de API

Clone o projeto:

git clone https://github.com/eliascastrosousa/SistemadeGerenciamentodeBiblioteca-java.git

Entre no diretório da aplicação:

cd SistemadeGerenciamentodeBiblioteca-java/sgb/sgb/

Suba os containers:

docker compose up --build

As instruções de execução com Docker estão disponíveis no repositório.

---

💻 Executando localmente

Também é possível executar a aplicação diretamente na máquina.

Pré-requisitos

- Java 17
- Maven
- MySQL
- IDE de desenvolvimento
- Postman ou outra ferramenta para testes de API

Após configurar o banco de dados e as dependências do projeto, execute a aplicação pela sua IDE ou através do Maven.

---

☁️ Deploy

O projeto também contempla uma estrutura de implantação utilizando:

AWS EC2
   │
   ▼
Nginx
   │
   ▼
Spring Boot
   │
   ├──── Redis
   │
   └──── MySQL

A utilização de Docker, Nginx e EC2 permite praticar não apenas o desenvolvimento da API, mas também conceitos relacionados à infraestrutura e disponibilização de aplicações backend.

---

🧠 Principais conceitos aplicados

Backend

- Java 17
- Spring Boot
- APIs REST
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- DTOs
- Regras de negócio
- Tratamento de dados

Banco de dados

- MySQL
- Modelagem relacional
- Persistência com JPA/Hibernate

Performance

- Redis
- Cache

Infraestrutura

- Docker
- Docker Compose
- Nginx
- AWS EC2

Desenvolvimento

- Maven
- Git
- GitHub
- Postman
- Swagger/OpenAPI

---

📈 Possíveis evoluções

Algumas funcionalidades que podem ser incorporadas ao projeto no futuro:

- [ ] Melhorar a documentação da arquitetura
- [ ] Expandir cobertura de testes automatizados
- [ ] Implementar testes de integração
- [ ] Melhorar observabilidade e logs
- [ ] Adicionar métricas da aplicação
- [ ] Implementar CI/CD
- [ ] Evoluir o sistema de notificações de atrasos
- [ ] Criar dashboard para acompanhamento da biblioteca
- [ ] Implementar paginação nos endpoints
- [ ] Melhorar o gerenciamento de cache

---

👨‍💻 Autor

Elias Castro Sousa Jr.

Analista de Sistemas | Java | Spring Boot | Oracle | SQL

""GitHub" (https://img.shields.io/badge/GitHub-Elias%20Castro-181717?style=for-the-badge&logo=github)" (https://github.com/eliascastrosousa)

---

📄 Licença

Este projeto está disponível para fins de estudo e desenvolvimento.

---

⭐ Gostou do projeto? Deixe uma estrela no repositório!

"🔗 Acessar o projeto no GitHub" (https://github.com/eliascastrosousa/SistemadeGerenciamentodeBiblioteca-java)
