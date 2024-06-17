package com.unesp.bibliotecaapi.dto.exemplar;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NovosExemplaresDto {
    private Long idLivro;
    private Integer quantidade;
}
