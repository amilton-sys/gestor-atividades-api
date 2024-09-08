package com.sys.gestaoatividades.integration.controller;

import com.sys.gestaoatividades.api.assembler.AtividadeMapper;
import com.sys.gestaoatividades.api.controller.AtividadeController;
import com.sys.gestaoatividades.api.model.AtividadeInput;
import com.sys.gestaoatividades.api.model.AtividadeModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtividadeController.class)
public class AtividadeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadastroAtividade cadastroAtividade;

    @MockBean
    private AtividadeRepository atividadeRepository;

    @MockBean
    private AtividadeMapper atividadeMapper;

    @Test
    void testCadastrar() throws Exception {
        AtividadeInput atividadeInput = new AtividadeInput("Atividade 1");
        Atividade atividade = new Atividade();
        atividade.setId(1);
        AtividadeModel atividadeModel = new AtividadeModel(1, "Atividade 1", null, null);

        given(atividadeMapper.toObject(atividadeInput)).willReturn(atividade);
        given(cadastroAtividade.cadastrar(atividade)).willReturn(atividade);
        given(atividadeMapper.toModel(atividade)).willReturn(atividadeModel);

        mockMvc.perform(post("/atividades")
                        .contentType("application/json")
                        .content("{\"nome\":\"Atividade 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.nome", equalTo("Atividade 1")));
    }

    @Test
    void testIniciar() throws Exception {
        mockMvc.perform(put("/atividades/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFinalizar() throws Exception {
        mockMvc.perform(delete("/atividades/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListar() throws Exception {
        Atividade atividade1 = new Atividade();
        Atividade atividade2 = new Atividade();
        List<Atividade> atividades = Arrays.asList(atividade1, atividade2);
        AtividadeModel atividadeModel1 = new AtividadeModel(1, "Atividade 1", null, null);
        AtividadeModel atividadeModel2 = new AtividadeModel(2, "Atividade 2", null, null);
        List<AtividadeModel> atividadeModels = Arrays.asList(atividadeModel1, atividadeModel2);

        given(atividadeRepository.findAll()).willReturn(atividades);
        given(atividadeMapper.toCollectionModel(atividades)).willReturn(atividadeModels);

        mockMvc.perform(get("/atividades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].nome", equalTo("Atividade 1")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].nome", equalTo("Atividade 2")));
    }
}
