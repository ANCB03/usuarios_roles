package com.examen.mapper;

import com.examen.dto.RolMenuDto;
import com.examen.entity.Rol;
import com.examen.entity.RolMenu;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMenuMapper {

    RolMenu toEntity(RolMenuDto rolMenuDto);
    RolMenuDto toDto(RolMenu rolMenu);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RolMenuDto rolMenuDto, @MappingTarget RolMenu rolMenu);

}
