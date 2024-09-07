package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.ParticipanteMapper;
import com.sys.gestaoatividades.api.model.ParticipanteModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import com.sys.gestaoatividades.domain.service.CadastroParticipanteAtividade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atividades/{atividadeId}/participantes")
public class AtividadeCadastroParticipanteController {
    private final CadastroParticipanteAtividade cadastroParticipanteAtividade;
    private final CadastroAtividade cadastroAtividade;
    private final ParticipanteMapper participanteMapper;

    public AtividadeCadastroParticipanteController(CadastroParticipanteAtividade cadastroParticipanteAtividade, CadastroAtividade cadastroAtividade, ParticipanteMapper participanteMapper) {
        this.cadastroParticipanteAtividade = cadastroParticipanteAtividade;
        this.cadastroAtividade = cadastroAtividade;
        this.participanteMapper = participanteMapper;
    }

    @PutMapping("{participanteId}")
    public ResponseEntity<Void> associar(@PathVariable Integer atividadeId, @PathVariable Integer participanteId) {
        cadastroParticipanteAtividade.associar(atividadeId, participanteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{participanteId}/presente")
    public ResponseEntity<Void> presente(@PathVariable Integer atividadeId, @PathVariable Integer participanteId) {
        cadastroParticipanteAtividade.presente(atividadeId, participanteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("presentes")
    public List<ParticipanteModel> presentes(@PathVariable Integer atividadeId) {
        return participanteMapper.toCollectionModel(cadastroParticipanteAtividade.listarPresentes(atividadeId));
    }

    @GetMapping
    public List<ParticipanteModel> listar(@PathVariable Integer atividadeId) {
        Atividade atividade = cadastroAtividade.buscar(atividadeId);
        return participanteMapper.toCollectionModel(atividade.getParticipantes());
    }
}
