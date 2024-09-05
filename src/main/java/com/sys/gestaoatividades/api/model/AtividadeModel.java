package com.sys.gestaoatividades.api.model;

import java.time.LocalDate;


public record AtividadeModel(
        Integer id,
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
