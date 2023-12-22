package com.examen.mapper;

import com.examen.dto.OpcionMenuCompletoDto;
import com.examen.entity.OpcionMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpcionMenuCompletoMapper {

    OpcionMenu toEntity(OpcionMenuCompletoDto opcionMenuCompletoDto);

    OpcionMenuCompletoDto toDto(OpcionMenu opcionMenu);
}
