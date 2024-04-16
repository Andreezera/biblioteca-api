package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    Editora findByNome(String nome);
}
