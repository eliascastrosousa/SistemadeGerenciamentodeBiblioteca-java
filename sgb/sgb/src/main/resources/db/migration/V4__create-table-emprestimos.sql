create table emprestimos(
    id bigint not null auto_increment,
    isbn varchar(13) not null,
    cpf varchar(11) not null,
    data_emprestimo datetime not null,
    data_devolucao datetime not null,
    ativo tinyint,
    multa float,

    primary key(id),
    constraint fk_emprestimos_livro_id foreign key(isbn) references livros(isbn),
    constraint fk_emprestimos_aluno_id foreign key(cpf) references alunos(cpf)
);