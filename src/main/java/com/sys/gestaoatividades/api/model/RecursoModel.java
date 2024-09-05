package com.sys.gestaoatividades.api.model;

public record RecursoModel(
        Integer id,
        String nome,
        String tipo,
        boolean disponivel
) {

}
