package dev.eliascastro.sgb.infra.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, String> {
    Page<Livro> findAllByAtivoTrueAndDisponivelTrue(Pageable paginacao);

    Livro findByIsbn(String isbn);

    Optional<Livro> getReferenceByIsbn(String isbn);

    boolean existsByIsbnAndAtivoFalse(String isbn);

    boolean existsByIsbn(String isbn);

    boolean existsByIsbnAndDisponivelFalse(String isbn);
}
