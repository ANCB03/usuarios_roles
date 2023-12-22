package com.examen.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opcionmenu")
public class OpcionMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 200)
    private String url;

    @Column(length = 100)
    private String icono;

    @JsonIgnoreProperties("opcionmenu")
    @OneToMany(mappedBy = "opcionmenu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolMenu> rolmenus = new ArrayList<>();
}
