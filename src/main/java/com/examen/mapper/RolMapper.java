package com.examen.mapper;

import com.examen.dto.OpcionMenuDto;
import com.examen.dto.RolDto;
import com.examen.entity.OpcionMenu;
import com.examen.entity.Rol;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity(RolDto rolDto);
    RolDto toDto(Rol rol);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RolDto rolDto, @MappingTarget Rol rol);
}
