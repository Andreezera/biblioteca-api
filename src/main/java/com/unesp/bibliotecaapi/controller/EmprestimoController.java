package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.cliente.NovoEmprestimoDto;
import com.unesp.bibliotecaapi.model.Categoria;
import com.unesp.bibliotecaapi.model.Cliente;
import com.unesp.bibliotecaapi.model.Emprestimo;
import com.unesp.bibliotecaapi.model.Exemplar;
import com.unesp.bibliotecaapi.repository.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarEmprestimo(@RequestBody NovoEmprestimoDto request) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setDataPrevistaDevolucao(request.getDataDevolucao());
        emprestimo.setFoiDevolvido(false);

        Optional<Cliente> cliente = clienteRepository.findById(request.getIdCliente());
        if (cliente.isEmpty())
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Cliente não encontrado"));

        Optional<Exemplar> exemplar = exemplarRepository.findById(request.getExemplarId());
        if (exemplar.isEmpty())
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Exemplar não encontrado"));

        emprestimo.setCliente(cliente.get());
        emprestimo.setExemplar(exemplar.get());

        Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo);
        return new ResponseEntity<>(novoEmprestimo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Emprestimo> getAllEmprestimo(@RequestParam(required = false) Boolean atrasado,
                                             @RequestParam(required = false) Boolean ativo) {
        if (atrasado != null && atrasado) {
            return emprestimoRepository.findByDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(new Date());
        } else if (ativo != null) {
            if (ativo)
                return emprestimoRepository.findByDataDevolucaoIsNull();
            else
                return emprestimoRepository.findByDataDevolucaoIsNotNull();

        } else
            return emprestimoRepository.findAll();
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);

        if (emprestimo == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        emprestimo.setDataDevolucao(new Date());
        emprestimo.setFoiDevolvido(true);
        Emprestimo emprestimoDevolvido = emprestimoRepository.save(emprestimo);

        return new ResponseEntity<>(emprestimoDevolvido, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Emprestimo> getEmprestimosByClienteId(@PathVariable Long clienteId,
                                                      @RequestParam(required = false) Boolean atrasado,
                                                      @RequestParam(required = false) Boolean ativo) {
        if (ativo != null && ativo)
            return getEmprestimosAtivosByClienteId(clienteId, atrasado);
        else
            return emprestimoRepository.findByClienteId(clienteId);
    }

    private List<Emprestimo> getEmprestimosAtivosByClienteId(Long clienteId, Boolean atrasado) {
        if (atrasado != null && atrasado)
            return emprestimoRepository.findByClienteIdAndDataPrevistaDevolucaoBeforeAndDataDevolucaoIsNull(clienteId, new Date());
        else
            return emprestimoRepository.findByClienteIdAndDataDevolucaoIsNull(clienteId);
    }
}