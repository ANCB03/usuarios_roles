package com.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {
    @Positive
    private int id;

    @NotEmpty(message = "se requiere el nombre del rol")
    private String nombre;

    private String descripcion;
}
