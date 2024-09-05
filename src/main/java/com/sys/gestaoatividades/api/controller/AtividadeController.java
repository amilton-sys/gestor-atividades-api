package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.AtividadeMapper;
import com.sys.gestaoatividades.api.model.AtividadeInput;
import com.sys.gestaoatividades.api.model.AtividadeModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import com.sys.gestaoatividades.domain.repository.AtividadeRepository;
import com.sys.gestaoatividades.domain.service.CadastroAtividade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("atividades")
public class AtividadeController {
    private final CadastroAtividade cadastroAtividade;
    private final AtividadeRepository atividadeRepository;
    private final AtividadeMapper atividadeMapper;

    public AtividadeController(CadastroAtividade cadastroAtividade, AtividadeRepository atividadeRepository, AtividadeMapper atividadeMapper) {
        this.cadastroAtividade = cadastroAtividade;
        this.atividadeRepository = atividadeRepository;
        this.atividadeMapper = atividadeMapper;
    }

    @PostMapping
    public ResponseEntity<AtividadeModel> cadastrar(@RequestBody @Valid AtividadeInput atividadeInput, UriComponentsBuilder uriBuilder) {
        Atividade atividade = atividadeMapper.toObject(atividadeInput);
        Atividade atividadeCadastrada = cadastroAtividade.cadastrar(atividade);
        var uri = uriBuilder.path("/atividades/{id}").buildAndExpand(atividadeCadastrada.getId()).toUri();

        AtividadeModel atividadeModel = atividadeMapper.toModel(atividadeCadastrada);

        return ResponseEntity.created(uri).body(atividadeModel);
    }

    @GetMapping
    public List<AtividadeModel> listar() {
        List<Atividade> atividades = atividadeRepository.findAll();
        return atividadeMapper.toCollectionModel(atividades);
    }
}
