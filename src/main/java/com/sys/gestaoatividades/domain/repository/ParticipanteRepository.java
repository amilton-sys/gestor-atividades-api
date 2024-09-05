package com.sys.gestaoatividades.domain.repository;

import com.sys.gestaoatividades.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
}
