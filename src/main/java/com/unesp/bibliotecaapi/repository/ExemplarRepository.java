package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    @Query("SELECT COUNT(e) FROM Exemplar e WHERE e.livro.id = :livroId")
    Integer countByLivroId(Long livroId);

    @Query("SELECT e FROM Exemplar e WHERE NOT EXISTS (" +
            "SELECT em FROM Emprestimo em WHERE em.exemplar = e AND em.foiDevolvido = false)" +
            "ORDER BY e.livro.id")
    List<Exemplar> findExemplaresDisponiveis();
}
