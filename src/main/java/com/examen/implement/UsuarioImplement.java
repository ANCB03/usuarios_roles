package com.examen.implement;

import com.examen.dto.UsuarioDto;
import com.examen.entity.Rol;
import com.examen.entity.Usuario;
import com.examen.exception.NotFoundException;
import com.examen.mapper.UsuarioMapper;
import com.examen.repository.RolRepository;
import com.examen.repository.UsuarioRepository;
import com.examen.service.UsuarioService;
import com.examen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UsuarioImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        List<Usuario> usuarios = usuarioRepository.findAll();

        if(!usuarios.isEmpty()){
            for (Usuario usuario: usuarios) {
                UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
                usuariosDto.add(usuarioDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("usuariosEmpty",null, Locale.getDefault()));
        }

        return usuariosDto;
    }

    @Override
    public void guardar(UsuarioDto usuarioDto) {
        if(usuarioRepository.findById(usuarioDto.getNumeroDocumento()).isEmpty()) {
            Usuario usuario = usuarioMapper.toEntity(usuarioDto);
            usuarioRepository.save(usuario);
        }else {
            throw new NotFoundException(messageUtil.getMessage("usuarioExist",null, Locale.getDefault()));
        }
    }

    @Override
    public void eliminar(String documento) {

        usuarioRepository.findById(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("musculoNotFound",null, Locale.getDefault()))
        );

        usuarioRepository.deleteById(documento);
    }

    @Override
    public UsuarioDto encontrarUsuarioByDocumento(String documento) {

        return usuarioMapper.toDto(usuarioRepository.findById(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public UsuarioDto editarUsuario(String documento, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()))
        );
        if(usuarioDto.getNombre()!=null)
            usuario.setNombre(usuario.getNombre());
        if(usuarioDto.getApellido()!=null)
            usuario.setApellido(usuarioDto.getApellido());
        if(usuarioDto.getTipoDocumento()!=null)
            usuario.setTipoDocumento(usuario.getTipoDocumento());
        if(usuarioDto.getEmail()!=null)
            usuario.setEmail(usuario.getEmail());
        if(usuarioDto.getTelefono()!=null)
            usuario.setTelefono(usuario.getTelefono());
        Rol rol = rolRepository.findById(usuarioDto.getRol().getId()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        if(usuarioDto.getRol()!=null)
            usuario.setRol(rol);

        usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}
