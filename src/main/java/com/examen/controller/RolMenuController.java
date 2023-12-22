package com.examen.controller;

import com.examen.dto.RolMenuDto;
import com.examen.service.RolMenuService;
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
@RequestMapping("/rolmenu")
@CrossOrigin
@Slf4j
public class RolMenuController {
    @Autowired
    private RolMenuService rolMenuService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarRolMenus(){
        response.clear();
        response.put("rolmenus",rolMenuService.listarRolMenus());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarRolMenu(@PathVariable int id) {
        response.clear();
        response.put("rolmenu", rolMenuService.encontrarRolMenuById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRolMenu(@Valid @RequestBody RolMenuDto rolMenuDto) throws IOException {
        response.clear();
        rolMenuService.guardar(rolMenuDto);
        response.put("message","Rol menu guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRolMenu(@PathVariable int id) {
        response.clear();
        rolMenuService.eliminar(id);
        response.put("message","Rol menu eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRolMenu(@PathVariable int id,@Valid @RequestBody RolMenuDto rolMenuDto) throws IOException {
        response.clear();
        RolMenuDto menu = rolMenuService.editarRolMenu(id,rolMenuDto);
        response.put("message", "Rol menu actualizado satisfactoriamente");
        response.put("rolmenu", menu);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
