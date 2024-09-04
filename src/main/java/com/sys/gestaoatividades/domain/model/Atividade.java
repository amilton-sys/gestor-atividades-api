package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atividade {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToMany
    @JoinColumn(name = "atividade_id")
    private List<Participante> participantes;
    @OneToMany
    @JoinColumn(name = "atividade_id")
    private List<Recurso> recursos;
}
