package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Recurso;
import com.sys.gestaoatividades.domain.repository.RecursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastroRecurso {
    private final RecursoRepository recursoRepository;

    public CadastroRecurso(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @Transactional
    public Recurso cadastrar(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public Recurso buscar(Integer id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Recurso de id %d não foi encontrada.", id)));
    }
}
