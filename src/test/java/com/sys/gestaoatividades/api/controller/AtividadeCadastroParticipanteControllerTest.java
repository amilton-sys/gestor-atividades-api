package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.ParticipanteMapper;
import com.sys.gestaoatividades.api.model.EnderecoModel;
import com.sys.gestaoatividades.api.model.ParticipanteModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.model.Participante;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import com.sys.gestaoatividades.domain.service.CadastroParticipanteAtividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtividadeCadastroParticipanteControllerTest {
    @InjectMocks
    private AtividadeCadastroParticipanteController atividadeCadastroParticipanteController;
    @Mock
    private CadastroParticipanteAtividade cadastroParticipanteAtividade;
    @Mock
    private CadastroAtividade cadastroAtividade;
    @Mock
    private ParticipanteMapper participanteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void associar() {
        ResponseEntity<Void> response = atividadeCadastroParticipanteController.associar(1, 1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cadastroParticipanteAtividade, times(1)).associar(1, 1);
    }

    @Test
    void presente() {
        ResponseEntity<Void> response = atividadeCadastroParticipanteController.presente(1, 1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cadastroParticipanteAtividade, times(1)).presente(1, 1);
    }

    @Test
    void presentes() {
        Atividade atividade = new Atividade();
        atividade.setId(1);

        Participante participante1 = new Participante();
        participante1.setPresente(true);

        Participante participante2 = new Participante();
        participante2.setPresente(true);

        List<Participante> participantes = Arrays.asList(participante1, participante2);

        ParticipanteModel participanteModel1 = new ParticipanteModel(1, "Amilton1", null, "amilton1@example.gmail.com", "81 9999999", true);
        ParticipanteModel participanteModel2 = new ParticipanteModel(2, "Amilton2", null, "amilton2@example.gmail.com", "82 9999999", true);


        List<ParticipanteModel> participanteModels = Arrays.asList(participanteModel1, participanteModel2);

        when(cadastroParticipanteAtividade.listarPresentes(atividade.getId())).thenReturn(participantes);
        when(participanteMapper.toCollectionModel(participantes)).thenReturn(participanteModels);

        List<ParticipanteModel> result = atividadeCadastroParticipanteController.presentes(atividade.getId());

        boolean presente = true;
        int tamanho = 2;

        assertEquals(tamanho, result.size());
        assertEquals(presente, result.get(0).presente());
        assertEquals(presente, result.get(1).presente());
        assertEquals("Amilton1", result.get(0).nome());
        assertEquals("Amilton2", result.get(1).nome());

        verify(cadastroParticipanteAtividade, times(1)).listarPresentes(atividade.getId());
    }
}