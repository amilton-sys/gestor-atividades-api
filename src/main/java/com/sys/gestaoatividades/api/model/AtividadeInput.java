package com.sys.gestaoatividades.api.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AtividadeInput {
    @NotBlank
    private String nome;
    @NotNull
    @FutureOrPresent
    private LocalDate dataInicio;
    @FutureOrPresent
    private LocalDate dataFim;
    @NotNull
    private Integer participanteId;
    @NotNull
    private Integer recursoId;

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @FutureOrPresent LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(@NotNull @FutureOrPresent LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public @FutureOrPresent LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(@FutureOrPresent LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public @NotNull Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(@NotNull Integer participanteId) {
        this.participanteId = participanteId;
    }

    public @NotNull Integer getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(@NotNull Integer recursoId) {
        this.recursoId = recursoId;
    }
}
