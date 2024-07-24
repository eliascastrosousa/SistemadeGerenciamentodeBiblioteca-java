create table usuarios(

    id bigint not null auto_increment,
    nome varchar(200) not null,
    login varchar(100) not null unique,
    senha varchar(255) not null,
    ativo tinyint,
    primary key(id)
);

