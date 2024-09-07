package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroAtividade {
    private final AtividadeRepository atividadeRepository;

    public CadastroAtividade(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    @Transactional
    public Atividade cadastrar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    @Transactional
    public void iniciar(Integer atividadeId) {
        Atividade atividade = buscar(atividadeId);
        if (atividade.isFinalizado()){
            throw new RuntimeException("A Atividade está finalizada.");
        }
        atividade.iniciar();
    }

    @Transactional
    public void finalizar(Integer atividadeId) {
        Atividade atividade = buscar(atividadeId);
        atividade.finalizar();
    }

    public Atividade buscar(Integer id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Atividade de id %d não foi encontrada.", id)));
    }
}
