package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface LivroRepository extends CrudRepository<Livro, Long> {
}
