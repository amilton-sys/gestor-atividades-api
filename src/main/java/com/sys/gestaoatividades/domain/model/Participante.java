package com.sys.gestaoatividades.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Participante {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private Pessoa pessoa;
    @Embedded
    private Endereco endereco;
    @Embedded
    private Contato contato;
    @ManyToOne
    private Atividade atividade;

    public boolean alreadyHaveOneActivity() {
        return this.getAtividade() != null;
    }
}
