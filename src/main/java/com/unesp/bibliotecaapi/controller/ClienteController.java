package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.cliente.NovoProfessorDto;
import com.unesp.bibliotecaapi.model.Cliente;
import com.unesp.bibliotecaapi.model.Professor;
import com.unesp.bibliotecaapi.repository.ClienteRepository;
import com.unesp.bibliotecaapi.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public Iterable<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
