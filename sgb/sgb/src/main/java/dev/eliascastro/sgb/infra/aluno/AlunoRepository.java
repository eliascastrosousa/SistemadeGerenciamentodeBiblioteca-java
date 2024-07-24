package dev.eliascastro.sgb.infra.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByCpfAndAtivoTrue(String cpf);

    Optional<Aluno> getReferenceByCpf(String cpf);

    Aluno findByCpf(String cpf);

    Optional<Aluno> findAtivoTrueByCpf(String cpf);
}
