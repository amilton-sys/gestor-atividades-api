package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.RecursoMapper;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import com.sys.gestaoatividades.domain.service.CadastroRecursoAtividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AtividadeCadastroRecursoControllerTest {
    @InjectMocks
    private AtividadeCadastroRecursoController cadastroRecursoController;
    @Mock
    private CadastroRecursoAtividade cadastroRecursoAtividade;
    @Mock
    private CadastroAtividade cadastroAtividade;
    @Mock
    private RecursoMapper recursoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void associar() {
        ResponseEntity<Void> response = cadastroRecursoController.associar(1, 1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cadastroRecursoAtividade, times(1)).associar(1, 1);
    }
}