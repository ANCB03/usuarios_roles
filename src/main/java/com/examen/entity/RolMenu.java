package com.examen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rolmenu")
public class RolMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_opcionmenu")
    private OpcionMenu opcionmenu;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
