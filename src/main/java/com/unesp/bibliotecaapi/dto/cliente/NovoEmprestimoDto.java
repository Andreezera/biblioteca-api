package com.unesp.bibliotecaapi.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class NovoEmprestimoDto {
        private Long idCliente;
        private Long exemplarId;
        private Date dataDevolucao;
}