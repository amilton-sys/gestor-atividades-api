package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
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
    @Enumerated(EnumType.STRING)
    private Status status = Status.INSCRICOES;

    @ManyToMany
    @JoinTable(name = "atividade_participante",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private Set<Participante> participantes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "atividade_recurso",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private Set<Recurso> recursos = new HashSet<>();

    public void associarParticipante(Participante participante) {
        getParticipantes().add(participante);
    }

    public void associar(Recurso recurso) {
        getRecursos().add(recurso);
    }

    public boolean isAfterInitActivity() {
        var today = LocalDate.now();
        return dataInicio.isBefore(today);
    }

    public void iniciar() {
        this.setDataInicio(LocalDate.now());
        this.setStatus(Status.INICIADA);
    }

    public void finalizar() {
        this.setDataFim(LocalDate.now());
        this.setStatus(Status.FINALIZADA);
    }

    public boolean isFinalizado() {
        return status == Status.FINALIZADA;
    }

    public boolean isInicializada() {
        return status == Status.INICIADA;
    }

    public boolean ifPresent(Participante participante) {
        return getParticipantes().contains(participante);
    }
}
