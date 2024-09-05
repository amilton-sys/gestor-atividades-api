package com.sys.gestaoatividades.api.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtividadeInput(
        @NotBlank
        String nome,
        @NotNull
        @FutureOrPresent
        LocalDate dataInicio,
        @NotNull
        @FutureOrPresent
        LocalDate dataFim
) {

}
