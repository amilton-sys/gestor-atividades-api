package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Atividade {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToMany
    @JoinTable(name = "atividade_participante",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantes;

    @OneToMany
    @JoinTable(name = "atividade_recurso",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private List<Recurso> recursos;

    public boolean isDatesValid() {
        return this.dataInicio.isBefore(this.dataFim);
    }
}
