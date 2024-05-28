package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.model.Autor;
import com.unesp.bibliotecaapi.repository.AutorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/autores")
@Tag(name = "Autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public Iterable<Autor> getAllAutores() {
        return autorRepository.findAll();
    }
}