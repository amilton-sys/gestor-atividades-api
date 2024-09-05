package com.sys.gestaoatividades.api.controller;

import com.sys.gestaoatividades.api.assembler.EnderecoMapper;
import com.sys.gestaoatividades.api.assembler.ParticipanteMapper;
import com.sys.gestaoatividades.api.model.ParticipanteInput;
import com.sys.gestaoatividades.api.model.ParticipanteModel;
import com.sys.gestaoatividades.domain.model.Endereco;
import com.sys.gestaoatividades.domain.model.Participante;
import com.sys.gestaoatividades.domain.repository.ParticipanteRepository;
import com.sys.gestaoatividades.domain.service.CadastroParticipante;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("participantes")
public class ParticipanteController {
    private final CadastroParticipante cadastroParticipante;
    private final ParticipanteRepository participanteRepository;
    private final ParticipanteMapper participanteMapper;
    private final EnderecoMapper enderecoMapper;

    public ParticipanteController(CadastroParticipante cadastroParticipante, ParticipanteRepository participanteRepository, ParticipanteMapper participanteMapper, EnderecoMapper enderecoMapper) {
        this.cadastroParticipante = cadastroParticipante;
        this.participanteRepository = participanteRepository;
        this.participanteMapper = participanteMapper;
        this.enderecoMapper = enderecoMapper;
    }


    @PostMapping
    public ResponseEntity<ParticipanteModel> cadastrar(@RequestBody @Valid ParticipanteInput participanteInput, UriComponentsBuilder uriBuilder) {

        System.out.println("Recebido ParticipanteInput: " + participanteInput);
        Participante participante = participanteMapper.toObject(participanteInput);
        System.out.println("Participante: " + participante);
        Participante participanteCadastrado = cadastroParticipante.cadastrar(participante);
        System.out.println("ParticipanteCadastrado: " + participanteCadastrado);

        var uri = uriBuilder.path("/recursos/{id}").buildAndExpand(participanteCadastrado.getId()).toUri();

        ParticipanteModel participanteModel = participanteMapper.toModel(participanteCadastrado);

        return ResponseEntity.created(uri).body(participanteModel);
    }

    @GetMapping
    public List<ParticipanteModel> listar() {
        List<Participante> participantes = participanteRepository.findAll();
        return participanteMapper.toCollectionModel(participantes);
    }
}
