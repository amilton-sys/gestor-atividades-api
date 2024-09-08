package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.RecursoMapper;
import com.sys.gestaoatividades.api.model.RecursoInput;
import com.sys.gestaoatividades.api.model.RecursoModel;
import com.sys.gestaoatividades.domain.model.Recurso;
import com.sys.gestaoatividades.domain.repository.RecursoRepository;
import com.sys.gestaoatividades.domain.service.CadastroRecurso;
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

class RecursoControllerTest {
    @InjectMocks
    private RecursoController recursoController;
    @Mock
    private CadastroRecurso cadastroRecurso;
    @Mock
    private RecursoRepository recursoRepository;
    @Mock
    private RecursoMapper recursoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrar() {
        RecursoInput recursoInput = new RecursoInput("Tv", "Equipamento");
        Recurso recurso = new Recurso();

        Recurso recursoCadastrado = new Recurso();
        recursoCadastrado.setId(1);

        RecursoModel recursoModel = new RecursoModel(1, "Tv", "Equipamento", true);

        when(recursoMapper.toObject(recursoInput)).thenReturn(recurso);
        when(cadastroRecurso.cadastrar(recurso)).thenReturn(recursoCadastrado);
        when(recursoMapper.toModel(recursoCadastrado)).thenReturn(recursoModel);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        ResponseEntity<RecursoModel> response = recursoController.cadastrar(recursoInput);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recursoModel, response.getBody());
        URI expectedUri = uriBuilder.path("/recursos/{id}").buildAndExpand(1).toUri();
        assertEquals(expectedUri, response.getHeaders().getLocation());
        verify(cadastroRecurso, times(1)).cadastrar(recurso);
    }

    @Test
    void listar() {
        Recurso recurso1 = new Recurso();
        Recurso recurso2 = new Recurso();
        List<Recurso> recursos = Arrays.asList(recurso1, recurso2);

        RecursoModel recursoModel1 = new RecursoModel(1, "Tv", "Equipamento", true);
        RecursoModel recursoModel2 = new RecursoModel(2, "Tv", "Equipamento", true);
        List<RecursoModel> recursoModels = Arrays.asList(recursoModel1, recursoModel2);

        when(recursoRepository.findAll()).thenReturn(recursos);
        when(recursoMapper.toCollectionModel(recursos)).thenReturn(recursoModels);

        List<RecursoModel> result = recursoController.listar();

        assertEquals(2, result.size());
        assertEquals(recursoModel1, result.get(0));
        assertEquals(recursoModel2, result.get(1));
        verify(recursoRepository, times(1)).findAll();
    }
}