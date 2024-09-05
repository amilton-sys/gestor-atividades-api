package com.sys.gestaoatividades.api.model;

import jakarta.validation.constraints.NotBlank;

public record EnderecoInput(
        @NotBlank
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        String bairro
) {
}
