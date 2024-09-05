package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.RecursoMapper;
import com.sys.gestaoatividades.api.model.RecursoModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import com.sys.gestaoatividades.domain.service.CadastroRecursoAtividade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atividades/{atividadeId}/recursos")
public class AtividadeCadastroRecursoController {
    private final CadastroRecursoAtividade cadastroRecursoAtividade;
    private final CadastroAtividade cadastroAtividade;
    private final RecursoMapper recursoMapper;

    public AtividadeCadastroRecursoController(CadastroRecursoAtividade cadastroRecursoAtividade, CadastroAtividade cadastroAtividade, RecursoMapper recursoMapper) {
        this.cadastroRecursoAtividade = cadastroRecursoAtividade;
        this.cadastroAtividade = cadastroAtividade;
        this.recursoMapper = recursoMapper;
    }

    @PutMapping("{recursoId}")
    public ResponseEntity<Void> associar(@PathVariable Integer atividadeId, @PathVariable Integer recursoId) {
        cadastroRecursoAtividade.associar(atividadeId, recursoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<RecursoModel> listar(@PathVariable Integer atividadeId) {
        Atividade atividade = cadastroAtividade.buscar(atividadeId);
        return recursoMapper.toCollectionModel(atividade.getRecursos());
    }
}
