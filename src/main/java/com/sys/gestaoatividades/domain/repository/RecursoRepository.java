package com.sys.gestaoatividades.domain.repository;

import com.sys.gestaoatividades.domain.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}
