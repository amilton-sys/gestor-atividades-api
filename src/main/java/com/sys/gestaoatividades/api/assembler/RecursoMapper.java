package com.sys.gestaoatividades.api.assembler;

import com.sys.gestaoatividades.api.model.RecursoInput;
import com.sys.gestaoatividades.api.model.RecursoModel;
import com.sys.gestaoatividades.domain.model.Recurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RecursoMapper {
    RecursoModel toModel(Recurso recurso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disponivel", ignore = true)
    Recurso toObject(RecursoInput recursoInput);

    List<RecursoModel> toCollectionModel(Collection<Recurso> recursos);
}
