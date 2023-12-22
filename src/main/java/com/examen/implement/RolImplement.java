package com.examen.implement;

import com.examen.dto.RolCompletoDto;
import com.examen.dto.RolDto;
import com.examen.entity.Rol;
import com.examen.exception.NotFoundException;
import com.examen.mapper.RolCompletoMapper;
import com.examen.mapper.RolMapper;
import com.examen.repository.RolRepository;
import com.examen.service.RolService;
import com.examen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RolImplement implements RolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolMapper rolMapper;

    @Autowired
    private RolCompletoMapper rolCompletoMapper;


    @Override
    public List<RolDto> listarRoles() {
        List<RolDto> rolesDto = new ArrayList<>();
        List<Rol> roles = rolRepository.findAll();
        if(!roles.isEmpty()) {
            for (Rol rol : roles
            ) {
                RolDto rolDto = rolMapper.toDto(rol);
                rolesDto.add(rolDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("rolesEmpty",null, Locale.getDefault()));
        }
        return rolesDto;
    }

    @Override
    public void guardar(RolDto rolDto) {

        Rol rol = rolMapper.toEntity(rolDto);
        rolRepository.save(rol);
    }

    @Override
    public void eliminar(int id) {

        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        rolRepository.deleteById(id);
    }

    @Override
    public RolCompletoDto encontrarRolById(int id) {
        return rolCompletoMapper.toDto(rolRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public RolDto editarRol(int id, RolDto rolDto) {
        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        rolMapper.updateEntity(rolDto,rol);
        rolRepository.save(rol);
        return rolMapper.toDto(rol);
    }
}
