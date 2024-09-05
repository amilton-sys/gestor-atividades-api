package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Participante;
import com.sys.gestaoatividades.domain.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroParticipante {
    private final ParticipanteRepository participanteRepository;

    public CadastroParticipante(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Transactional
    public void cadastrar(Participante participante) {
        participanteRepository.save(participante);
    }

    public Participante buscar(Integer id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Participante de id %d não foi encontrado.", id)));
    }
}
