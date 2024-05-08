ALTER TABLE usuarios ADD ativo tinyint;
update usuarios set ativo = 1;