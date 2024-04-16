package com.unesp.bibliotecaapi.dto.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NovoLivroDto {
    private String nome;
    private Integer ano;
    private String categoria;
    private String editora;
    private String autor;
}
