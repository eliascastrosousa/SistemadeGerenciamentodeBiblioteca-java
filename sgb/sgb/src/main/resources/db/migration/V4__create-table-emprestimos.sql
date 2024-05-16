create table emprestimos(
    id bigint not null auto_increment,
    livro_id bigint not null,
    aluno_id bigint not null,
    data_emprestimo datetime not null,
    data_devolucao datetime not null,
    ativo tinyint,
    multa float,

    primary key(id),
    constraint fk_emprestimos_livro_id foreign key(livro_id) references livros(id),
    constraint fk_emprestimos_aluno_id foreign key(aluno_id) references alunos(id)
);