package com.examen.implement;

import com.examen.dto.OpcionMenuCompletoDto;
import com.examen.dto.OpcionMenuDto;
import com.examen.entity.OpcionMenu;
import com.examen.exception.NotFoundException;
import com.examen.mapper.OpcionMenuCompletoMapper;
import com.examen.mapper.OpcionMenuMapper;
import com.examen.repository.OpcionMenuRepository;
import com.examen.service.OpcionMenuService;
import com.examen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class OpcionMenuImplement implements OpcionMenuService {

    @Autowired
    private OpcionMenuRepository opcionMenuRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private OpcionMenuMapper opcionMenuMapper;

    @Autowired
    private OpcionMenuCompletoMapper opcionMenuCompletoMapper;

    @Override
    public List<OpcionMenuDto> listarOpcionesMenu() {
        List<OpcionMenuDto> menusDto = new ArrayList<>();

        List<OpcionMenu> menus = opcionMenuRepository.findAll();

        if(!menus.isEmpty()){
            for (OpcionMenu menu: menus) {
                OpcionMenuDto opcionMenuDto = opcionMenuMapper.toDto(menu);
                menusDto.add(opcionMenuDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("menusEmpty",null, Locale.getDefault()));
        }

        return menusDto;
    }

    @Override
    public void guardar(OpcionMenuDto opcionMenuDto) {

        OpcionMenu opcionMenu = opcionMenuMapper.toEntity(opcionMenuDto);
        opcionMenuRepository.save(opcionMenu);
    }

    @Override
    public void eliminar(int id) {

        opcionMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("menuNotFound",null, Locale.getDefault()))
        );

        opcionMenuRepository.deleteById(id);
    }

    @Override
    public OpcionMenuCompletoDto encontrarOpcionMenuById(int id) {
        return opcionMenuCompletoMapper.toDto(opcionMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("menuNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public OpcionMenuDto editarOpcionMenu(int id, OpcionMenuDto opcionMenuDto) {
        OpcionMenu opcionMenu = opcionMenuRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("menuNotFound",null, Locale.getDefault()))
        );
        opcionMenuMapper.updateEntity(opcionMenuDto,opcionMenu);
        opcionMenuRepository.save(opcionMenu);
        return opcionMenuMapper.toDto(opcionMenu);
    }
}
