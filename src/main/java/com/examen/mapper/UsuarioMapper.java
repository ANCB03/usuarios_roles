package com.examen.mapper;

import com.examen.dto.UsuarioDto;
import com.examen.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto usuarioDto);

    UsuarioDto toDto(Usuario usuario);

}
