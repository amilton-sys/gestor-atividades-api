package com.sys.gestaoatividades.domain.service;

import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CadastroAtividadeTest {

    @InjectMocks
    private CadastroAtividade cadastroAtividade;

    @Mock
    private AtividadeRepository atividadeRepository;

    @Mock
    private Atividade atividade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        // Arrange
        when(atividadeRepository.save(atividade)).thenReturn(atividade);

        // Act
        Atividade result = cadastroAtividade.cadastrar(atividade);

        // Assert
        assertEquals(atividade, result);
        verify(atividadeRepository, times(1)).save(atividade);
    }

    @Test
    void testIniciarComAtividadeNaoFinalizada() {
        // Arrange
        when(atividadeRepository.findById(1)).thenReturn(java.util.Optional.of(atividade));
        when(atividade.isFinalizado()).thenReturn(false);

        // Act
        cadastroAtividade.iniciar(1);

        // Assert
        verify(atividade, times(1)).iniciar();
    }

    @Test
    void testIniciarComAtividadeFinalizada() {
        // Arrange
        when(atividadeRepository.findById(1)).thenReturn(java.util.Optional.of(atividade));
        when(atividade.isFinalizado()).thenReturn(true);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            cadastroAtividade.iniciar(1);
        });
        assertEquals("A Atividade está finalizada.", thrown.getMessage());
    }

    @Test
    void testFinalizar() {
        // Arrange
        when(atividadeRepository.findById(1)).thenReturn(java.util.Optional.of(atividade));

        // Act
        cadastroAtividade.finalizar(1);

        // Assert
        verify(atividade, times(1)).finalizar();
    }

    @Test
    void testBuscarAtividadeNaoEncontrada() {
        // Arrange
        when(atividadeRepository.findById(1)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            cadastroAtividade.buscar(1);
        });
        assertEquals("Atividade de id 1 não foi encontrada.", thrown.getMessage());
    }
}
