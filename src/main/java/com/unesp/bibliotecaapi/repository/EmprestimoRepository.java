package com.unesp.bibliotecaapi.repository;

import com.unesp.bibliotecaapi.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(Date data);

    List<Emprestimo> findByClienteId(Long clienteId);

    List<Emprestimo> findByClienteIdAndDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(Long clienteId, Date data);

    List<Emprestimo> findByDataDevolucaoIsNull();

    List<Emprestimo> findByDataDevolucaoIsNotNull();

    List<Emprestimo> findByClienteIdAndDataDevolucaoIsNull(Long clienteId);
}
