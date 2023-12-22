package com.examen.dto;

import com.examen.entity.RolMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolCompletoDto {

    @Positive
    private int id;

    @NotEmpty(message = "se requiere el nombre del rol")
    private String nombre;

    private String descripcion;

    private List<UsuarioDto> usuarios = new ArrayList<>();

    private List<RolMenuDto> rolmenus = new ArrayList<>();
}
