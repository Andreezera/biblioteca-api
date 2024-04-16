package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.model.Editora;
import com.unesp.bibliotecaapi.repository.EditoraRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editoras")
@Tag(name = "Editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping
    public Iterable<Editora> getAllEditoras() {
        return editoraRepository.findAll();
    }
}