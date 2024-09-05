package com.sys.gestaoatividades.domain.repository;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
    @Query("FROM Atividade a LEFT JOIN a.participantes WHERE a.id = :id")
    List<Participante> findParticipantesById(Integer id);
}
