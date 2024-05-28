package com.unesp.bibliotecaapi.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NovoProfessorDto {
    private String nome;
    private String email;
    private String cpf;
    private Long telefone;
    private Long rp;
    private String departamento;
}
