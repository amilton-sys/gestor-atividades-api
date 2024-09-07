package com.sys.gestaoatividades.api.assembler;

import com.sys.gestaoatividades.api.model.ParticipanteInput;
import com.sys.gestaoatividades.api.model.ParticipanteModel;
import com.sys.gestaoatividades.domain.model.Participante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface ParticipanteMapper {
    ParticipanteModel toModel(Participante participante);


    @Mapping(target = "presente", ignore = true)
    @Mapping(target = "id", ignore = true)
    Participante toObject(ParticipanteInput participanteInput);

    List<ParticipanteModel> toCollectionModel(Collection<Participante> participantes);
}
