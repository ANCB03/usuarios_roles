package com.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {


    @NotEmpty(message = "Se requiere el documento.")
    private String numeroDocumento;

    @NotEmpty(message = "Se requiere el tipo de documento")
    private String TipoDocumento;

    @NotEmpty(message = "Se requiere nombre")
    private String nombre;

    @NotEmpty(message = "Se requiere el apellido")
    private String apellido;

    @NotEmpty(message = "Se requiere el email")
    private String email;

    @NotEmpty(message = "Se requiere el password.")
    private String password;

    @NotEmpty(message = "Se requiere el telefono")
    private String telefono;

    private RolDto rol;
}
