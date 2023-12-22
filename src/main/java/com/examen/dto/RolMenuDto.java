package com.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolMenuDto {

    @Positive
    private int id;

    private OpcionMenuDto opcionmenu;

    private RolDto rol;
}
