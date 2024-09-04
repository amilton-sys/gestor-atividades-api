package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contato {
    private String email;
    private String telefone;
}
