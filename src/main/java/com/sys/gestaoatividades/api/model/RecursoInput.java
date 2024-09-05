package com.sys.gestaoatividades.api.model;

import jakarta.validation.constraints.NotBlank;

public record RecursoInput(
        @NotBlank
        String nome,
        @NotBlank
        String tipo
) {

}
