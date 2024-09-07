package com.sys.gestaoatividades.api.model;

import jakarta.validation.constraints.NotBlank;

public record AtividadeInput(
        @NotBlank
        String nome
) {

}
