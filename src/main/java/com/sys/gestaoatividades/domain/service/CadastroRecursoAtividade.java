package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Recurso;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroRecursoAtividade {
    private final CadastroRecurso cadastroRecurso;
    private final CadastroAtividade cadastroAtividade;

    public CadastroRecursoAtividade(CadastroRecurso cadastroRecurso, CadastroAtividade cadastroAtividade) {
        this.cadastroRecurso = cadastroRecurso;
        this.cadastroAtividade = cadastroAtividade;
    }

    @Transactional
    public void associar(Integer atividadeId, Integer recursoId) {
        Atividade atividade = cadastroAtividade.buscar(atividadeId);
        Recurso recurso = cadastroRecurso.buscar(recursoId);
        if (!recurso.isDisponivel()) {
            throw new RuntimeException("Recurso indisponível no momento.");
        }
        atividade.associar(recurso);
    }

}
