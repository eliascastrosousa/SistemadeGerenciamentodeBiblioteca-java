create table livros(
    id bigint not null auto_increment,
    titulo varchar(200) not null,
    autor varchar(100) not null,
    isbn varchar(100) not null,
    genero varchar(100) not null,
    disponivel tinyint,

    primary key(id)
)