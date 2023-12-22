package com.examen.service;


import com.examen.dto.OpcionMenuCompletoDto;
import com.examen.dto.OpcionMenuDto;

import java.util.List;

public interface OpcionMenuService {
    public List<OpcionMenuDto> listarOpcionesMenu();

    public void guardar(OpcionMenuDto opcionMenuDto);

    public void eliminar(int id);

    public OpcionMenuCompletoDto encontrarOpcionMenuById(int id);

    public OpcionMenuDto editarOpcionMenu(int id, OpcionMenuDto opcionMenuDto);
}
