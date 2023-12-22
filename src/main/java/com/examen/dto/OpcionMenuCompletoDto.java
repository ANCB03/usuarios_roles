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
public class OpcionMenuCompletoDto {

    @Positive
    private int id;

    @NotEmpty(message = "Se requiere el nombre")
    private String nombre;

    @NotEmpty(message = "Se requiere la url")
    private String url;

    @NotEmpty(message = "Se requiere el icono")
    private String icono;

    private List<RolMenuDto> rolmenus = new ArrayList<>();
}
