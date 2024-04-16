package com.unesp.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Editora editora;

    @ManyToOne
    private Autor autor;
}
