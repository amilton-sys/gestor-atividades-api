package com.sys.gestaoatividades.api.assembler;

import com.sys.gestaoatividades.api.model.AtividadeInput;
import com.sys.gestaoatividades.api.model.AtividadeModel;
import com.sys.gestaoatividades.domain.model.Atividade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtividadeMapper {
    AtividadeModel toModel(Atividade atividade);

    @Mapping(target = "recursos", ignore = true)
    @Mapping(target = "participantes", ignore = true)
    @Mapping(target = "id", ignore = true)
    Atividade toObject(AtividadeInput atividadeInput);

    List<AtividadeModel> toCollectionModel(List<Atividade> atividades);
}
