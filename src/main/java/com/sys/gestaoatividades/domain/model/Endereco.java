package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String numero;
    private String complemento;
    private String bairro;
}
