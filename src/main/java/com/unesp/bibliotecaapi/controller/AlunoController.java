package com.unesp.bibliotecaapi.controller;

import com.unesp.bibliotecaapi.dto.cliente.NovoAlunoDto;
import com.unesp.bibliotecaapi.model.Aluno;
import com.unesp.bibliotecaapi.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes/alunos")
@Tag(name = "Alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public Iterable<Aluno> getAllAlunoes() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        return optionalAluno.map(aluno -> ResponseEntity.ok().body(aluno))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody NovoAlunoDto alunoDto) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(alunoDto.getNome());
        novoAluno.setEmail(alunoDto.getEmail());
        novoAluno.setCpf(alunoDto.getCpf());
        novoAluno.setTelefone(alunoDto.getTelefone());
        novoAluno.setRa(alunoDto.getRa());
        novoAluno.setCurso(alunoDto.getCurso());

        Aluno alunoSalvo = alunoRepository.save(novoAluno);

        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody NovoAlunoDto alunoDto) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoDto.getNome());
            aluno.setEmail(alunoDto.getEmail());
            aluno.setCpf(alunoDto.getCpf());
            aluno.setTelefone(alunoDto.getTelefone());
            aluno.setRa(alunoDto.getRa());
            aluno.setCurso(alunoDto.getCurso());

            Aluno alunoAtualizado = alunoRepository.save(aluno);
            return ResponseEntity.ok().body(alunoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}