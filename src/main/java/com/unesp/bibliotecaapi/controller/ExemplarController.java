package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.exemplar.NovosExemplaresDto;
import com.unesp.bibliotecaapi.model.Exemplar;
import com.unesp.bibliotecaapi.model.Livro;
import com.unesp.bibliotecaapi.repository.ExemplarRepository;
import com.unesp.bibliotecaapi.repository.LivroRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livros/exemplares")
@Tag(name = "Exemplares")
public class ExemplarController {

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Exemplar> getAllExemplares() { return exemplarRepository.findAll(); }

    @PostMapping
    public ResponseEntity<?> createExemplar(@RequestBody NovosExemplaresDto novosExemplares) {

        Livro livro = livroRepository.findById(novosExemplares.getIdLivro()).orElse(null);
        if (livro == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Livro não encontrado"));
        }

        for (int i = 0; i < novosExemplares.getQuantidade(); i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLivro(livro);

            exemplarRepository.save(exemplar);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("message", novosExemplares.getQuantidade() + " exemplares criados com sucesso"));
    }

    @GetMapping("/{id}")
    public Exemplar getExemplarById(@PathVariable Long id) {
        return exemplarRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteExemplar(@PathVariable Long id) {
        exemplarRepository.deleteById(id);
    }
}
