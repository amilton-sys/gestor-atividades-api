package com.sys.gestaoatividades.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipanteInput(
        @NotNull
        String nome,
        @NotNull
        @Valid
        EnderecoInput endereco,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone
) {
}
