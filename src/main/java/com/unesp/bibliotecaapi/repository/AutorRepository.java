package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNome(String nome);
}