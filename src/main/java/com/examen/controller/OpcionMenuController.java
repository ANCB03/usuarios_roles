package com.examen.controller;

import com.examen.dto.OpcionMenuDto;
import com.examen.service.OpcionMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/opcionmenu")
@CrossOrigin
@Slf4j
public class OpcionMenuController {
    @Autowired
    private OpcionMenuService opcionMenuService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarOpcionMenus(){
        response.clear();
        response.put("opcionmenus",opcionMenuService.listarOpcionesMenu());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarOpcionMenu(@PathVariable int id) {
        response.clear();
        response.put("opcionmenu", opcionMenuService.encontrarOpcionMenuById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarOpcionMenu(@Valid @RequestBody OpcionMenuDto opcionMenuDto) throws IOException {
        response.clear();
        opcionMenuService.guardar(opcionMenuDto);
        response.put("message","Opcion menu guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOpcionMenu(@PathVariable int id) {
        response.clear();
        opcionMenuService.eliminar(id);
        response.put("message","Opcion menu eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarOpcionMenu(@PathVariable int id,@Valid @RequestBody OpcionMenuDto opcionMenuDto) throws IOException {
        response.clear();
        OpcionMenuDto menu = opcionMenuService.editarOpcionMenu(id,opcionMenuDto);
        response.put("message", "Opcion menu actualizado satisfactoriamente");
        response.put("opcionmenu", menu);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
