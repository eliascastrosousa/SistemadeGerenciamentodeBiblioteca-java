package dev.eliascastro.sgb.infra.emprestimo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Page<Emprestimo> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByIdAndAtivoTrue(Long id);

//    @Query("""
//            SELECT
//                      emp.id AS id_emprestimo,
//                      alu.nome AS nome_aluno,
//                      liv.titulo AS titulo_livro,
//                      emp.data_emprestimo,
//                      emp.data_devolucao
//                  FROM
//                      emprestimos AS emp
//                  INNER JOIN
//                      alunos AS alu ON emp.aluno_id = alu.id
//                  INNER JOIN
//                      livros AS liv ON emp.livro_id = liv.id
//                  WHERE
//                      emp.ativo = 1;
//            """)
//    Page<Emprestimo> findAllByAtivoTrueComDetalhes(Pageable paginacao);
}
