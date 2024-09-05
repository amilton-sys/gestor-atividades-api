package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Participante;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroParticipanteAtividade {
    private final CadastroAtividade cadastroAtividade;
    private final CadastroParticipante cadastroParticipante;

    public CadastroParticipanteAtividade(CadastroAtividade cadastroAtividade, CadastroParticipante cadastroParticipante) {
        this.cadastroAtividade = cadastroAtividade;
        this.cadastroParticipante = cadastroParticipante;
    }

    @Transactional
    public void associar(Integer atividadeId, Integer participanteId) {
        Atividade atividade = cadastroAtividade.buscar(atividadeId);
        Participante participante = cadastroParticipante.buscar(participanteId);
        atividade.associarParticipante(participante);
    }

}
