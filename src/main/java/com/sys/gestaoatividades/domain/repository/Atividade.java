package com.sys.gestaoatividades.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Atividade extends JpaRepository<Atividade, Long> {
}
