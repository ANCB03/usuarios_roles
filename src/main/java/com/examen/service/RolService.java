package com.examen.service;



import com.examen.dto.RolCompletoDto;
import com.examen.dto.RolDto;

import java.util.List;

public interface RolService {
    public List<RolDto> listarRoles();

    public void guardar(RolDto rolDto);

    public void eliminar(int id);

    public RolCompletoDto encontrarRolById(int id);

    public RolDto editarRol(int id, RolDto rolDto);
}
