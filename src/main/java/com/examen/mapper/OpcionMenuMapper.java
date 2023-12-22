package com.examen.mapper;

import com.examen.dto.OpcionMenuDto;
import com.examen.entity.OpcionMenu;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OpcionMenuMapper {

    OpcionMenu toEntity(OpcionMenuDto opcionMenuDto);
    OpcionMenuDto toDto(OpcionMenu opcionMenu);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(OpcionMenuDto opcionMenuDto, @MappingTarget OpcionMenu opcionMenu);
}
