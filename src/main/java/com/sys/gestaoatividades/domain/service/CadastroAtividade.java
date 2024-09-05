package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Participante;
import com.sys.gestaoatividades.domain.model.Recurso;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroAtividade {
    private final AtividadeRepository atividadeRepository;
    private final CadastroRecurso cadastroRecurso;
    private final CadastroParticipante cadastroParticipante;

    public CadastroAtividade(AtividadeRepository atividadeRepository, CadastroRecurso cadastroRecurso, CadastroParticipante cadastroParticipante) {
        this.atividadeRepository = atividadeRepository;
        this.cadastroRecurso = cadastroRecurso;
        this.cadastroParticipante = cadastroParticipante;
    }

    @Transactional
    public Atividade cadastrar(Atividade atividade, Integer recursoId, Integer participanteId) {
        Participante participante = cadastroParticipante.buscar(participanteId);
        if (participante.alreadyHaveOneActivity()) {
            throw new RuntimeException("O participante já pertence a uma atividade.");
        }
        participante.setAtividade(atividade);
        Recurso recurso = cadastroRecurso.buscar(recursoId);
        if (!recurso.isDisponivel()){
            throw new RuntimeException("O Recurso não está disponivel no momento.");
        }
        recurso.setAtividade(atividade);
        return atividadeRepository.save(atividade);
    }

    public Atividade buscar(Integer id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Atividade de id %d não foi encontrada.", id)));
    }
}
