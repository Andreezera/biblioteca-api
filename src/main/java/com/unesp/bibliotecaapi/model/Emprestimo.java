package com.unesp.bibliotecaapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dataEmprestimo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dataPrevistaDevolucao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dataDevolucao = null;
    private Boolean foiDevolvido;

    @ManyToOne
    @JoinColumn(name = "exemplar_id")
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}