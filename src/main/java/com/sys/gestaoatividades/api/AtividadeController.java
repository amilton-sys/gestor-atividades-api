package com.sys.gestaoatividades.api;

import com.sys.gestaoatividades.api.model.AtividadeInput;
import com.sys.gestaoatividades.api.model.AtividadeModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("atividades")
public class AtividadeController {
    private final CadastroAtividade cadastroAtividade;

    public AtividadeController(CadastroAtividade cadastroAtividade) {
        this.cadastroAtividade = cadastroAtividade;
    }

    @PostMapping
    public ResponseEntity<AtividadeModel> cadastrar(@RequestBody @Valid AtividadeInput atividadeInput, UriComponentsBuilder uriBuilder) {
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeInput.getNome());
        atividade.setDataInicio(atividadeInput.getDataInicio());
        atividade.setDataFim(atividadeInput.getDataFim());

        Atividade atividadeCadastrada = cadastroAtividade.cadastrar(atividade, atividadeInput.getRecursoId(), atividadeInput.getParticipanteId());

        var uri = uriBuilder.path("/atividades/{id}").buildAndExpand(atividadeCadastrada.getId()).toUri();

        AtividadeModel atividadeModel = new AtividadeModel();
        atividadeModel.setId(atividadeCadastrada.getId());
        atividadeModel.setNome(atividadeCadastrada.getNome());
        atividadeModel.setDataInicio(atividadeCadastrada.getDataInicio());
        atividadeModel.setDataFim(atividadeCadastrada.getDataFim());

        return ResponseEntity.created(uri).body(atividadeModel);
    }
}
