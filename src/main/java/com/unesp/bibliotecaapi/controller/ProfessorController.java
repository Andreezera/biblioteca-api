package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.cliente.NovoProfessorDto;
import com.unesp.bibliotecaapi.model.Editora;
import com.unesp.bibliotecaapi.model.Livro;
import com.unesp.bibliotecaapi.model.Professor;
import com.unesp.bibliotecaapi.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/clientes/professores")
@Tag(name = "Professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public Iterable<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        return optionalProfessor.map(professor -> ResponseEntity.ok().body(professor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody NovoProfessorDto professorDto) {
        Professor novoProfessor = new Professor();
        novoProfessor.setNome(professorDto.getNome());
        novoProfessor.setEmail(professorDto.getEmail());
        novoProfessor.setCpf(professorDto.getCpf());
        novoProfessor.setTelefone(professorDto.getTelefone());
        novoProfessor.setRp(professorDto.getRp());
        novoProfessor.setDepartamento(professorDto.getDepartamento());

        Professor professorSalvo = professorRepository.save(novoProfessor);

        return new ResponseEntity<>(professorSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody NovoProfessorDto professorDto) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setNome(professorDto.getNome());
            professor.setEmail(professorDto.getEmail());
            professor.setCpf(professorDto.getCpf());
            professor.setTelefone(professorDto.getTelefone());
            professor.setRp(professorDto.getRp());
            professor.setDepartamento(professorDto.getDepartamento());

            Professor professorAtualizado = professorRepository.save(professor);
            return ResponseEntity.ok().body(professorAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            professorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
