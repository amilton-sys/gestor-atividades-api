package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {
    private String numero;
    private String complemento;
    private String bairro;
}
