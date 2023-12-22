package com.examen.service;

import com.examen.dto.UsuarioDto;
import com.examen.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public List<UsuarioDto> listarUsuarios();

    public void guardar(UsuarioDto usuarioDto);

    public void eliminar(String documento);

    public UsuarioDto encontrarUsuarioByDocumento(String documento);

    public UsuarioDto editarUsuario(String documento, UsuarioDto usuarioDto);

}
