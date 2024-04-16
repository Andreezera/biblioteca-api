package com.unesp.bibliotecaapi.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Professor extends Cliente {
    private Long rp;
    private String departamento;
}