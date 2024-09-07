package com.sys.gestaoatividades.domain.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Participante;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!atividade.isAfterInitActivity()) {
            throw new RuntimeException("Não é possível se cadastrar após o inicio da atividade.");
        }
        atividade.associarParticipante(participante);
    }

    @Transactional
    public void presente(Integer atividadeId, Integer participanteId) {
        Atividade atividade = cadastroAtividade.buscar(atividadeId);
        if (!atividade.isInicializada()){
            throw new RuntimeException("Está atividade ainda não foi inicializada.");
        }
        Participante participante = cadastroParticipante.buscar(participanteId);
        boolean present = atividade.ifPresent(participante);
        if (!present) {
            throw new RuntimeException("Este participante não pertence a está atividade.");
        }
        participante.setPresente(true);
    }

    public List<Participante> listarPresentes(Integer atividadeId) {
        return cadastroAtividade.buscar(atividadeId).getParticipantes().stream()
                .filter(Participante::isPresente).toList();
    }
}
