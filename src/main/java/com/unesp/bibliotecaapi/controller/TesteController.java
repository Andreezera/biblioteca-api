package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.model.Categoria;
import com.unesp.bibliotecaapi.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/teste")
@Tag(name = "Teste")
public class TesteController {

    @GetMapping
    public ResponseEntity getTest() {
        return new ResponseEntity("teste", HttpStatus.OK);
    }
}