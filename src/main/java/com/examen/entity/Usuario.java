package com.examen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @Column(unique=true, nullable = false)
    private String numeroDocumento;

    @Column(length = 25)
    private String tipoDocumento;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(length = 50)
    private String email;

    @Column(length = 10)
    private String telefono;

    @Column(length = 450)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
