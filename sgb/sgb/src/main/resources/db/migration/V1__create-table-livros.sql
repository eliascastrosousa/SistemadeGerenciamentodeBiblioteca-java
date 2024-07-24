create table livros(

    titulo varchar(200) not null,
    autor varchar(100) not null,
    isbn varchar(13) not null unique,
    categoria_livro varchar(100) not null,
    disponivel tinyint,
    ativo tinyint,

    primary key(isbn)
);

