package com.examen.service;



import com.examen.dto.RolMenuDto;
import com.examen.entity.RolMenu;

import java.util.List;

public interface RolMenuService {
    public List<RolMenuDto> listarRolMenus();

    public void guardar(RolMenuDto rolMenuDto);

    public void eliminar(int id);

    public RolMenuDto encontrarRolMenuById(int id);

    public RolMenuDto editarRolMenu(int id, RolMenuDto rolMenuDto);
}
