package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.livro.NovoLivroDto;
import com.unesp.bibliotecaapi.dto.livro.LivroDto;
import com.unesp.bibliotecaapi.model.Autor;
import com.unesp.bibliotecaapi.model.Categoria;
import com.unesp.bibliotecaapi.model.Editora;
import com.unesp.bibliotecaapi.model.Livro;
import com.unesp.bibliotecaapi.repository.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros")
public class LivrosController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @GetMapping
    public List<LivroDto> getAll() {
        List<LivroDto> livroDtoList = new ArrayList<>();
        livroRepository.findAll().forEach(livro -> {

            Integer qtdExemplares = exemplarRepository.countByLivroId(livro.getId());

            LivroDto livroDTO = new LivroDto(
                    livro.getId(),
                    livro.getNome(),
                    livro.getAno(),
                    livro.getCategoria().getNome(),
                    livro.getEditora().getNome(),
                    livro.getAutor().getNome(),
                    qtdExemplares
            );
            livroDtoList.add(livroDTO);
        });
        return livroDtoList;
    }

    @PostMapping
    public ResponseEntity<?> inserirLivro(@RequestBody NovoLivroDto novoLivroDto) {

        Livro novoLivro = new Livro();
        novoLivro.setNome(novoLivroDto.getNome());
        novoLivro.setAno(novoLivroDto.getAno());

        Categoria categoria = categoriaRepository.findByNome(novoLivroDto.getCategoria());
        if (categoria == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Categoria não encontrada"));
        }

        Editora editora = editoraRepository.findByNome(novoLivroDto.getEditora());
        if (editora == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Editora não encontrada"));
        }

        Autor autor = autorRepository.findByNome(novoLivroDto.getAutor());
        if (autor == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Autor não encontrado"));
        }

        novoLivro.setCategoria(categoria);
        novoLivro.setEditora(editora);
        novoLivro.setAutor(autor);

        Livro livroSalvo = livroRepository.save(novoLivro);

        LivroDto livroSalvoDTO = new LivroDto(
                livroSalvo.getId(),
                livroSalvo.getNome(),
                livroSalvo.getAno(),
                livroSalvo.getCategoria().getNome(),
                livroSalvo.getEditora().getNome(),
                livroSalvo.getAutor().getNome(),
                0
        );

        return new ResponseEntity<>(livroSalvoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody LivroDto livroDTO) {

        if (!livroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Livro livroExistente = livroRepository.findById(id).orElse(null);
        if (livroExistente == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Livro não encontrado"));
        }

        livroExistente.setNome(livroDTO.getNome());
        livroExistente.setAno(livroDTO.getAno());

        Categoria categoria = categoriaRepository.findByNome(livroDTO.getCategoria());
        if (categoria == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Categoria não encontrada"));
        }

        Editora editora = editoraRepository.findByNome(livroDTO.getEditora());
        if (editora == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Editora não encontrada"));
        }

        Autor autor = autorRepository.findByNome(livroDTO.getAutor());
        if (autor == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Autor não encontrado"));
        }

        Integer qtdExemplares = exemplarRepository.countByLivroId(livroDTO.getId());

        livroExistente.setCategoria(categoria);
        livroExistente.setEditora(editora);
        livroExistente.setAutor(autor);

        Livro livroAtualizado = livroRepository.save(livroExistente);

        LivroDto livroAtualizadoDTO = new LivroDto(
                livroAtualizado.getId(),
                livroAtualizado.getNome(),
                livroAtualizado.getAno(),
                livroAtualizado.getCategoria().getNome(),
                livroAtualizado.getEditora().getNome(),
                livroAtualizado.getAutor().getNome(),
                qtdExemplares
        );
        return ResponseEntity.ok(livroAtualizadoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivroById(@PathVariable Long id) {

        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        Integer qtdExemplares = exemplarRepository.countByLivroId(id);

        LivroDto livroDTO = new LivroDto(
                livro.getId(),
                livro.getNome(),
                livro.getAno(),
                livro.getCategoria().getNome(),
                livro.getEditora().getNome(),
                livro.getAutor().getNome(),
                qtdExemplares
        );
        return ResponseEntity.ok(livroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {

        if (!livroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
