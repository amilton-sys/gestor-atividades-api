package com.sys.gestaoatividades.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Atividade {
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Participante> participantes;
}
