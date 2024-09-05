package com.sys.gestaoatividades.api.assembler;

import com.sys.gestaoatividades.api.model.EnderecoInput;
import com.sys.gestaoatividades.api.model.EnderecoModel;
import com.sys.gestaoatividades.domain.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoModel toModel(Endereco endereco);

    Endereco toObject(EnderecoInput enderecoInput);
}
