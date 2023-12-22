package com.examen.implement;

import com.examen.dto.RolMenuDto;
import com.examen.entity.OpcionMenu;
import com.examen.entity.Rol;
import com.examen.entity.RolMenu;
import com.examen.exception.NotFoundException;
import com.examen.mapper.RolMenuMapper;
import com.examen.repository.OpcionMenuRepository;
import com.examen.repository.RolMenuRepository;
import com.examen.repository.RolRepository;
import com.examen.service.RolMenuService;
import com.examen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RolMenuImplement implements RolMenuService {
    @Autowired
    private RolMenuRepository rolMenuRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolMenuMapper rolMenuMapper;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private OpcionMenuRepository opcionMenuRepository;

    @Override
    public List<RolMenuDto> listarRolMenus() {
        List<RolMenuDto> rolMenuDtos = new ArrayList<>();

        List<RolMenu> rolMenus = rolMenuRepository.findAll();

        if(!rolMenus.isEmpty()){
            for (RolMenu rolMenu: rolMenus) {
                RolMenuDto rolMenuDto = rolMenuMapper.toDto(rolMenu);
                rolMenuDtos.add(rolMenuDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("rolMenuEmpty",null, Locale.getDefault()));
        }

        return rolMenuDtos;
    }

    @Override
    public void guardar(RolMenuDto rolMenuDto) {

        RolMenu rolMenu = rolMenuMapper.toEntity(rolMenuDto);
        rolMenuRepository.save(rolMenu);
    }

    @Override
    public void eliminar(int id) {

        rolMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolMenuNotFound",null, Locale.getDefault()))
        );

        rolMenuRepository.deleteById(id);
    }

    @Override
    public RolMenuDto encontrarRolMenuById(int id) {
        return rolMenuMapper.toDto(rolMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolMenuNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public RolMenuDto editarRolMenu(int id, RolMenuDto rolMenuDto) {
        RolMenu rolMenu = rolMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolMenuNotFound",null, Locale.getDefault()))
        );
        Rol rol = rolRepository.findById(rolMenuDto.getRol().getId()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        if(rolMenuDto.getRol()!=null)
            rolMenu.setRol(rol);

        OpcionMenu opcionMenu = opcionMenuRepository.findById(rolMenuDto.getOpcionmenu().getId()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolMenuNotFound",null, Locale.getDefault()))
        );
        if(rolMenuDto.getOpcionmenu()!=null)
            rolMenu.setOpcionmenu(opcionMenu);

        rolMenuRepository.save(rolMenu);

        return rolMenuMapper.toDto(rolMenu);
    }
}
