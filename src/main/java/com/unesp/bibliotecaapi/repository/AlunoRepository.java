package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}