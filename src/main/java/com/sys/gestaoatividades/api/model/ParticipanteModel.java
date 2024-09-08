package com.sys.gestaoatividades.api.model;


public record ParticipanteModel(Integer id, String nome, EnderecoModel endereco, String email, String telefone, boolean presente) {
}
