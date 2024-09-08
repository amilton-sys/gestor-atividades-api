package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.AtividadeMapper;
import com.sys.gestaoatividades.api.model.AtividadeInput;
import com.sys.gestaoatividades.api.model.AtividadeModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtividadeControllerTest {

    @InjectMocks
    private AtividadeController atividadeController;
    @Mock
    private CadastroAtividade cadastroAtividade;
    @Mock
    private AtividadeRepository atividadeRepository;
    @Mock
    private AtividadeMapper atividadeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        // Arrange
        AtividadeInput atividadeInput = new AtividadeInput("Atividade 1");
        Atividade atividade = new Atividade();
        Atividade atividadeCadastrada = new Atividade();
        atividadeCadastrada.setId(1);
        AtividadeModel atividadeModel = new AtividadeModel(1, "Atividade 1", null, null);

        when(atividadeMapper.toObject(atividadeInput)).thenReturn(atividade);
        when(cadastroAtividade.cadastrar(atividade)).thenReturn(atividadeCadastrada);
        when(atividadeMapper.toModel(atividadeCadastrada)).thenReturn(atividadeModel);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        // Act
        ResponseEntity<AtividadeModel> response = atividadeController.cadastrar(atividadeInput);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(atividadeModel, response.getBody());
        URI expectedUri = uriBuilder.path("/atividades/{id}").buildAndExpand(1).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());
        verify(cadastroAtividade, times(1)).cadastrar(atividade);
    }

    @Test
    void testIniciar() {
        // Act
        ResponseEntity<Void> response = atividadeController.iniciar(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        verify(cadastroAtividade, times(1)).iniciar(1);
    }

    @Test
    void testFinalizar() {
        // Act
        ResponseEntity<Void> response = atividadeController.finalizar(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        verify(cadastroAtividade, times(1)).finalizar(1);
    }

    @Test
    void testListar() {
        // Arrange
        Atividade atividade1 = new Atividade();
        Atividade atividade2 = new Atividade();
        List<Atividade> atividades = Arrays.asList(atividade1, atividade2);
        AtividadeModel atividadeModel1 = new AtividadeModel(1, "Atividade 1", null, null);
        AtividadeModel atividadeModel2 = new AtividadeModel(2, "Atividade 2", null, null);
        List<AtividadeModel> atividadeModels = Arrays.asList(atividadeModel1, atividadeModel2);

        when(atividadeRepository.findAll()).thenReturn(atividades);
        when(atividadeMapper.toCollectionModel(atividades)).thenReturn(atividadeModels);

        // Act
        List<AtividadeModel> result = atividadeController.listar();

        // Assert
        assertEquals(2, result.size());
        assertEquals(atividadeModel1, result.get(0));
        assertEquals(atividadeModel2, result.get(1));
        verify(atividadeRepository, times(1)).findAll();
    }
}
