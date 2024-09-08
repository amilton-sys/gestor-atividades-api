package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.RecursoMapper;
import com.sys.gestaoatividades.api.model.RecursoInput;
import com.sys.gestaoatividades.api.model.RecursoModel;
import com.sys.gestaoatividades.domain.model.Recurso;
import com.sys.gestaoatividades.domain.repository.RecursoRepository;
import com.sys.gestaoatividades.domain.service.CadastroRecurso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("recursos")
public class RecursoController {
    private final CadastroRecurso cadastroRecurso;
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public RecursoController(CadastroRecurso cadastroRecurso, RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.cadastroRecurso = cadastroRecurso;
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @PostMapping
    public ResponseEntity<RecursoModel> cadastrar(@RequestBody @Valid RecursoInput recursoInput) {
        Recurso recurso = recursoMapper.toObject(recursoInput);
        Recurso recursoCadastrado = cadastroRecurso.cadastrar(recurso);

        var uri = UriComponentsBuilder.newInstance().path("/recursos/{id}").buildAndExpand(recursoCadastrado.getId()).toUri();

        RecursoModel recursoModel = recursoMapper.toModel(recursoCadastrado);

        return ResponseEntity.created(uri).body(recursoModel);
    }

    @GetMapping
    public List<RecursoModel> listar() {
        List<Recurso> recursos = recursoRepository.findAll();
        return recursoMapper.toCollectionModel(recursos);
    }
}
