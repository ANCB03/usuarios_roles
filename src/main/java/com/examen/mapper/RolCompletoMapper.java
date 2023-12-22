package com.examen.mapper;

import com.examen.dto.RolCompletoDto;
import com.examen.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolCompletoMapper {

    Rol toEntity(RolCompletoDto rolCompletoDto);

    RolCompletoDto toDto(Rol rol);
}
