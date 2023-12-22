package com.examen.controller;

import com.examen.dto.RolDto;
import com.examen.service.RolService;
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
@RequestMapping("/rol")
@CrossOrigin
@Slf4j
public class RolController {
    @Autowired
    private RolService rolService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarRoles(){
        response.clear();
        response.put("roles",rolService.listarRoles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarRol(@PathVariable int id) {
        response.clear();
        response.put("rol", rolService.encontrarRolById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRol(@Valid @RequestBody RolDto rolDto) throws IOException {
        response.clear();
        rolService.guardar(rolDto);
        log.info(rolDto.toString());
        response.put("message","Rol guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable int id) {

        response.clear();
        rolService.eliminar(id);
        response.put("message","Rol eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRol(@PathVariable int id,@Valid @RequestBody RolDto rolDto) throws IOException {
        response.clear();
        RolDto rol = rolService.editarRol(id,rolDto);
        response.put("message", "Rol actualizada satisfactoriamente");
        response.put("rol", rol);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
