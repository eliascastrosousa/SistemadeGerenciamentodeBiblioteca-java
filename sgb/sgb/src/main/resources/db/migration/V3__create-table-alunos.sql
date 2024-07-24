create table alunos(

    nome varchar(200) not null,
    cpf varchar(11) not null unique,
    email varchar(100) not null unique,
    telefone varchar(11) not null,

    logradouro varchar(100) not null,
    cep varchar(9) not null,
    numero varchar(20),
    complemento varchar(100),

    ativo tinyint,
    limite_livros integer,
    multa float,

    primary key(cpf)
);


