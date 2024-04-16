package com.unesp.bibliotecaapi.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Aluno extends Cliente {
    private Long ra;
    private String curso;
}