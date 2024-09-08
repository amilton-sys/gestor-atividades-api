package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.ParticipanteMapper;
import com.sys.gestaoatividades.api.model.EnderecoInput;
import com.sys.gestaoatividades.api.model.EnderecoModel;
import com.sys.gestaoatividades.api.model.ParticipanteInput;
import com.sys.gestaoatividades.api.model.ParticipanteModel;
import com.sys.gestaoatividades.domain.model.Participante;
import com.sys.gestaoatividades.domain.repository.ParticipanteRepository;
import com.sys.gestaoatividades.domain.service.CadastroParticipante;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class ParticipanteControllerTest {
    @InjectMocks
    private ParticipanteController participanteController;
    @Mock
    private CadastroParticipante cadastroParticipante;
    @Mock
    private ParticipanteRepository participanteRepository;
    @Mock
    private ParticipanteMapper participanteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        //Arrange
        EnderecoInput enderecoInput = new EnderecoInput("20", "próximo ao lider jato", "Planalto");
        ParticipanteInput participanteInput = new ParticipanteInput("Amilton", enderecoInput, "amilton@example.gmail.com", "81 9999999");
        Participante participante = new Participante();
        Participante participanteCadastrado = new Participante();
        participanteCadastrado.setId(1);
        EnderecoModel enderecoModel = new EnderecoModel("20", "próximo ao lider jato", "Planalto");
        ParticipanteModel participanteModel = new ParticipanteModel(1, "Amilton", enderecoModel, "amilton@example.gmail.com", "81 9999999",false);

        when(participanteMapper.toObject(participanteInput)).thenReturn(participante);
        when(cadastroParticipante.cadastrar(participante)).thenReturn(participanteCadastrado);
        when(participanteMapper.toModel(participanteCadastrado)).thenReturn(participanteModel);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        //Act
        ResponseEntity<ParticipanteModel> response = participanteController.cadastrar(participanteInput);

        //Assert
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertEquals(participanteModel, response.getBody());

        URI expectedUri = uriBuilder.path("/participantes/{id}").buildAndExpand(1).toUri();

        assertEquals(expectedUri, response.getHeaders().getLocation());

        verify(cadastroParticipante, times(1)).cadastrar(participante);
    }

    @Test
    void testListar() {
        Participante participante1 = new Participante();
        Participante participante2 = new Participante();
        List<Participante> participantes = Arrays.asList(participante1, participante2);

        EnderecoModel enderecoModel1 = new EnderecoModel("201213", "próximo ao lider jato13", "Planalto13");
        EnderecoModel enderecoModel2 = new EnderecoModel("202", "próximo ao lider jato2", "Planalto2");
        ParticipanteModel participanteModel1 = new ParticipanteModel(1, "Amilton1", enderecoModel1, "amilton1@example.gmail.com", "81 9999999",true);
        ParticipanteModel participanteModel2 = new ParticipanteModel(2, "Amilton2", enderecoModel2, "amilton2@example.gmail.com", "82 9999999",true);
        List<ParticipanteModel> participanteModels = Arrays.asList(participanteModel1, participanteModel2);

        when(participanteRepository.findAll()).thenReturn(participantes);
        when(participanteMapper.toCollectionModel(participantes)).thenReturn(participanteModels);

        List<ParticipanteModel> result = participanteController.listar();

        assertEquals(2, result.size());
        assertEquals(participanteModel1, result.get(0));
        assertEquals(participanteModel2, result.get(1));
        verify(participanteRepository, times(1)).findAll();
    }
}