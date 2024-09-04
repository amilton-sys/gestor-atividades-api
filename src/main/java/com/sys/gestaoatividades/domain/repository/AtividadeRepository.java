package com.sys.gestaoatividades.domain.repository;

import com.sys.gestaoatividades.domain.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
